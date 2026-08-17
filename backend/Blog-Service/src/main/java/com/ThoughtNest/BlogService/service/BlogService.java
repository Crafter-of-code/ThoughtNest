
package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.client.UserFeignClient;
import com.ThoughtNest.BlogService.dto.*;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.exceptions.blog.BlogNotFoundException;
import com.ThoughtNest.BlogService.exceptions.user.UserNotFoundException;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import com.ThoughtNest.BlogService.utility.CloudinaryUtility;
import com.ThoughtNest.BlogService.utility.JwtUtil;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class BlogService {
    private BlogRepository blogRepository;
    private UserFeignClient userFeignClient;
    private BlogIntractionService blogIntractionService;
    private Cloudinary cloudinary;
    private JwtUtil jwtUtil;
    private final CloudinaryUtility cloudinaryUtility;
    public ResponseDto uploadUserBlog(BlogRequestDto userBlog, String token){
        BlogEntity blogEntity = new BlogEntity();
        ResponseDto responseDto = new ResponseDto();
        UserDto userDto = userFeignClient.getOwnerDetail(token);
        try{
            if(userBlog.getBlogTitle() != "" && userBlog.getBlogContent() != ""){
                blogEntity.setUserId(userDto.getUserId());
                blogEntity.setUserName(userDto.getUserName());
                blogEntity.setUserEmail(userDto.getUserEmail());
                blogEntity.setBlogTitle(userBlog.getBlogTitle());
                blogEntity.setBlogContent(userBlog.getBlogContent());
                blogEntity.setUserPublicId(userBlog.getUserPublicId());
                blogEntity.setBlogViews(new ArrayList<>());
                blogEntity.setBlogLikes(new ArrayList<>());
                blogEntity.setBlogComment(new ArrayList<Map<String,String>>());
                blogEntity.setUserImageUrl(userDto.getUserImageUrl());
                System.out.println(blogEntity.getUserImageUrl());
                if(userBlog.getCoverImage() != null){
                    BlogCoverImageDto blogImageUploadedData = cloudinaryUtility.uploadCoverImage(
                            new BlogCoverImageDto(userBlog.getCoverImage(),null,null));
                    blogEntity.setCoverImage(blogImageUploadedData.getSecureUrl());
                    blogEntity.setCoverImagePublicUrl(blogImageUploadedData.getPublicUrl());
                }
                BlogEntity recentlyUploadedBlogInfo = blogRepository.save(blogEntity);
                BlogDto blogDto = new BlogDto();
                blogDto.setBlogId(recentlyUploadedBlogInfo.getBlogId());
                boolean isUserKnowAboutUpdate = userFeignClient.whenUserPublishBlog(token,blogDto);
                responseDto.setStatus(true);
                responseDto.setMessage("Your Blog has been published successfully");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            responseDto.setStatus(false);
            responseDto.setMessage("There is some problem While uploading your blog");
        }
        return responseDto;
    }
    @SuppressWarnings("rawtype")
    /* get single blog*/
    public ResponseDto<BlogDetailResponseDto> getSingleBlogService(String token, String blogId) {
        BlogEntity blogEntity = blogRepository.findById(blogId)
                .orElseThrow(() ->
                        new BlogNotFoundException("Unable to find the requested blog"));
        BlogDetailResponseDto dto = new BlogDetailResponseDto();
        dto.setBlogId(blogEntity.getBlogId());
        dto.setBlogTitle(blogEntity.getBlogTitle());
        dto.setBlogSummary(blogEntity.getBlogSummary());
        dto.setBlogContent(blogEntity.getBlogContent());
        dto.setCoverImage(blogEntity.getCoverImage());
        dto.setCreatedAt(blogEntity.getCreatedAt());
        dto.setBlogViews(blogEntity.getBlogViews().size());
        dto.setBlogLikes(blogEntity.getBlogLikes().size());
        dto.setBlogComments(blogEntity.getBlogComment().size());
        try {
            UserDto author = userFeignClient.getUserDetailByUserId(
                    token,
                    blogEntity.getUserId()
            );
            dto.setUserName(author.getUserName());
            dto.setUserPublicId(author.getPublicId());
            dto.setUserImageUrl(author.getUserImageUrl());
            blogIntractionService.recordView(author, blogEntity);
        } catch (UserNotFoundException ex) {
            dto.setUserName("Deleted User");
            dto.setUserPublicId(null);
            dto.setUserImageUrl("https://placehold.co/100x100?text=N/A");
        }
        try {
            UserDto currentUser = userFeignClient.getOwnerDetail(token);
            dto.setBlogLiked(
                    blogEntity.getBlogLikes()
                    .contains(currentUser.getPublicId()));
            blogIntractionService.recordView(currentUser,blogEntity);
        } catch (UserNotFoundException ex) {
            dto.setBlogLiked(false);
        }
        ResponseDto<BlogDetailResponseDto> response = new ResponseDto<>();
        response.setStatus(true);
        response.setMessage("Blog fetched successfully.");
        response.setData(dto);
        return response;
    }
    public ResponseDto blogLikeService(){
        return null;
    }
    public ResponseDto blogDeleteService(String token,String blogId){
        ResponseDto responseDto = new ResponseDto();
        Optional<BlogEntity> blogEntity = blogRepository.findByBlogIdAndUserEmail(blogId
                ,jwtUtil.extractUsername(token));
        if(blogEntity.isPresent()){
            try{
                Map result = cloudinary.uploader().destroy(blogEntity.get().getCoverImagePublicUrl()
                        ,ObjectUtils.emptyMap());
                blogRepository.deleteById(blogId);
                responseDto.setStatus(true);
                responseDto.setMessage("Successfully deleted");
            }catch (Exception e){
                responseDto.setStatus(false);
                responseDto.setMessage("unable to find the user blog");
            }
        }else{
                responseDto.setStatus(false);
                responseDto.setMessage("you are unauthrized to delete it");
        }
        return  responseDto;
    }
    public ResponseDto getTop3Blogs(String token, UUID publicId) {
        String userEmail = jwtUtil.extractUsername(token);
        List<BlogEntity> blogs;
        if (publicId == null) {
            blogs = blogRepository.findTop3ByUserEmailOrderByCreatedAtDesc(userEmail);
        } else {
            blogs = blogRepository.findTop3ByUserPublicIdOrderByCreatedAtDesc(publicId);
        }
        List<ShortBlogResponseDto> responseData = blogs.stream()
                .map(blog -> {
                    ShortBlogResponseDto dto = new ShortBlogResponseDto();
                    dto.setBlogId(blog.getBlogId());
                    dto.setBlogTitle(blog.getBlogTitle());
                    dto.setBlogContent(blog.getBlogContent());
                    dto.setPublicId(blog.getUserPublicId());
                    dto.setUserName(blog.getUserName());
                    dto.setCreatedAt(blog.getCreatedAt());
                    return dto;
                })
                .toList();
        ResponseDto response = new ResponseDto();
        response.setStatus(true);
        if (responseData.isEmpty()) {
            response.setMessage("No blogs found.");
        } else {
            response.setMessage("Blogs retrieved successfully.");
            response.setData(responseData);
        }
        return response;
    }

}
