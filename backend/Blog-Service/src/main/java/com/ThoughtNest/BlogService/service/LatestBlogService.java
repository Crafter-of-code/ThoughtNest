package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.client.UserFeignClient;
import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import com.ThoughtNest.BlogService.dto.UserDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LatestBlogService {
    private BlogRepository blogRepository;
    private UserFeignClient userFeignClient;
    @SuppressWarnings("rawtypes")
    public ResponseDto getLatestBlog(String token){
        int lengthOfString = 100;
        List<ShortBlogResponseDto> shortBlogResponseDtos = new ArrayList<ShortBlogResponseDto>();
        ResponseDto responseDto = new ResponseDto();
        System.out.println("Latest blog service has been called");
        try{
            Optional<List<BlogEntity>> blogEntities = blogRepository.findTop20ByOrderByCreatedAtDesc();

            if(blogEntities.isPresent()){
                List<BlogEntity> allBlogEntity = blogEntities.get();
                allBlogEntity.stream().forEach(item->{
                    ShortBlogResponseDto shortBlogResponseDto = new ShortBlogResponseDto();
                    shortBlogResponseDto.setBlogTitle(item.getBlogTitle());
                    shortBlogResponseDto.setBlogContent(item.getBlogContent().substring(0
                            ,Math.min(item.getBlogContent().length(),lengthOfString))+"...");
                    shortBlogResponseDto.setBlogId(item.getBlogId());
                    shortBlogResponseDto.setCreatedAt(item.getCreatedAt());
                    shortBlogResponseDto.setUserName(item.getUserName());
                    String userImageUrl = item.getUserImageUrl() != "" && item.getUserImageUrl() != null ? item.getUserImageUrl() :
                            "https://placehold.co/100x100?text=" + item.getUserName().charAt(0);
                    shortBlogResponseDto.setUserImageUrl(userImageUrl);
                    shortBlogResponseDtos.add(shortBlogResponseDto);
                });
                responseDto.setStatus(true);
                responseDto.setMessage("you are watching latest blogs posted");
                responseDto.setData(shortBlogResponseDtos);
            }
            else{
                responseDto.setStatus(false);
                responseDto.setMessage("you are watching latest blogs posted");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            responseDto.setStatus(false);
            responseDto.setMessage("We are receiving problem communicating to our backend service");
        }

        return  responseDto;
    }
}
