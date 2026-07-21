package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.client.UserFeignClient;
import com.ThoughtNest.BlogService.dto.BlogDetailResponseDto;
import com.ThoughtNest.BlogService.dto.BlogRequestDto;
import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.dto.UserDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogService {
    private BlogRepository blogRepository;
    private UserFeignClient userFeignClient;
    private Cloudinary cloudinary;
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
                   String url = uploadUrl.get("secure_url").toString();
                   blogEntity.setCoverImage(url);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    blogEntity.setCoverImage(null);
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
}
