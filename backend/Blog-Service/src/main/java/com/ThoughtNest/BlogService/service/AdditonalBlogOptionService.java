package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.client.UserFeignClient;
import com.ThoughtNest.BlogService.dto.AdditonaBlogOptionDto.BlogLikeRequestDto;
import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.dto.UserDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.exceptions.blog.BlogNotFoundException;
import com.ThoughtNest.BlogService.exceptions.user.UserNotFoundException;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdditonalBlogOptionService {
    private final BlogRepository blogRepository;
    private final UserFeignClient userFeignClient;
    private final BlogIntractionService blogIntractionService;
    /*Like and dislike both in single service*/
    @Transactional
    public ResponseDto blogLikeService(String token,BlogLikeRequestDto requestData){
        if(requestData.getBlogId() == null || requestData.getPublicId() == null){
            throw  new IllegalArgumentException("unable to find public id or blog id in your request");
        }
        UserDto userDto;
        BlogEntity blogEntity;
        try{
            userDto = userFeignClient.getOwnerDetail(token);
        }catch (Exception e){
            throw  new UserNotFoundException("Unable to find you");
        }
        System.out.println(requestData.getBlogId());
        blogEntity = blogRepository.findByBlogId(requestData
                .getBlogId()).orElseThrow(()->new BlogNotFoundException("Requested blog not found"));
        List<UUID> blogLikes = blogEntity.getBlogLikes();
        ResponseDto responseDto = new ResponseDto();
        if(blogLikes.contains(userDto.getPublicId())){
            blogIntractionService.recordUnlike(userDto,blogEntity);
            responseDto.setStatus(true);
            responseDto.setMessage("You likes has been removed successfully");
        }else{
            blogIntractionService.recordLike(userDto,blogEntity);
            responseDto.setStatus(true);
            responseDto.setMessage("You likes has been send successfully");
        }
        return  responseDto;
    }
}
