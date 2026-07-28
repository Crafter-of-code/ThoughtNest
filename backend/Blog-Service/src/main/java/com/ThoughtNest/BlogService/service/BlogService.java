package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.client.UserFeignClient;
import com.ThoughtNest.BlogService.dto.*;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import com.ThoughtNest.BlogService.utility.JwtUtil;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogService {
    private BlogRepository blogRepository;
    private UserFeignClient userFeignClient;
    private Cloudinary cloudinary;
    private JwtUtil jwtUtil;
    public ResponseDto uploadUserBlog(BlogRequestDto userBlog, String token){
        BlogEntity blogEntity = new BlogEntity();
        ResponseDto responseDto = new ResponseDto();
        UserDto userDto = userFeignClient.getUserDetail(token);
        try{
            if(userBlog.getBlogTitle() != "" && userBlog.getBlogContent() != ""){
                blogEntity.setUserId(userDto.getUserId());
                blogEntity.setUserName(userDto.getUserName());
                blogEntity.setUserEmail(userDto.getUserEmail());
                blogEntity.setBlogTitle(userBlog.getBlogTitle());
                blogEntity.setBlogContent(userBlog.getBlogContent());
                try{
                   Map uploadUrl = cloudinary.uploader().upload(userBlog.getCoverImage().getBytes(), ObjectUtils.emptyMap());

                   String secureUrl = uploadUrl.get("secure_url").toString();
                   String publicUrl = uploadUrl.get("public_id").toString();
                   blogEntity.setCoverImage(secureUrl);
                   blogEntity.setCoverImagePublicUrl(publicUrl);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    blogEntity.setCoverImage(null);
                    blogEntity.setCoverImagePublicUrl(null);
                }
                blogRepository.save(blogEntity);
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
    public ResponseDto getSingleBlogService(String blogId){
        ResponseDto responseDto = new ResponseDto();
       try{
           Optional<BlogDetailResponseDto> blogDetailResponseDto = blogRepository.findByBlogId(blogId);
           if(blogDetailResponseDto.isPresent()){
                BlogDetailResponseDto blogDetailResponseDto1 = blogDetailResponseDto.get();
                responseDto.setStatus(true);
                responseDto.setMessage("Here is your requested Blog");
                responseDto.setData(blogDetailResponseDto1);
           }
       }catch (Exception e){
           responseDto.setStatus(false);
           responseDto.setMessage("We are facing some error in the database");
           System.out.println(e.getMessage());
       }
        return responseDto;
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
     public ResponseDto getMy3BlogService(String token){
        ResponseDto responseDto = new ResponseDto();
        List<ShortBlogResponseDto> shortBlogResponseDtos = new ArrayList<ShortBlogResponseDto>();
        String userEmail = jwtUtil.extractUsername(token);
        try{
            List<BlogEntity> blogEntityList = blogRepository.findTop3ByUserEmailOrderByCreatedAtDesc(userEmail);
            if(blogEntityList.isEmpty()){
                responseDto.setStatus(true);
            responseDto.setMessage("Unable to find you blog");
            }else{
                blogEntityList.stream().forEach(item->{
                    ShortBlogResponseDto shortBlogResponseDto = new ShortBlogResponseDto();
                    shortBlogResponseDto.setBlogId(item.getBlogId());
                    shortBlogResponseDto.setBlogTitle(item.getBlogTitle());
                    shortBlogResponseDto.setBlogContent(item.getBlogContent());
                    shortBlogResponseDto.setPublicId(item.getPublicId());
                    shortBlogResponseDto.setUserName(item.getUserName());
                    shortBlogResponseDto.setCreatedAt(item.getCreatedAt());
                    shortBlogResponseDto.setPublicId(item.getPublicId());
                    shortBlogResponseDtos.add(shortBlogResponseDto);
                });
                responseDto.setStatus(true);
                responseDto.setMessage("We found you blog");
                responseDto.setData(shortBlogResponseDtos);
            }
        }catch (Exception e){
            responseDto.setStatus(false);
            responseDto.setMessage("unable wo find with that user name");
        }
        return  responseDto;
     }
}
