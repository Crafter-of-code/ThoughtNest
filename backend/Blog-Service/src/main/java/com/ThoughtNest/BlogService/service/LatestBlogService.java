package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LatestBlogService {
    private BlogRepository blogRepository;
    @SuppressWarnings("rawtypes")
    public ResponseDto getLatestBlog(){
        ResponseDto responseDto = new ResponseDto();
        try{
            Optional<List<ShortBlogResponseDto>> optionalBlogEntities = blogRepository.findTop20ByOrderByCreatedAtDesc();

            if(optionalBlogEntities.isPresent()){
                List<ShortBlogResponseDto> shortBlogResponseDtos = optionalBlogEntities.get();
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
