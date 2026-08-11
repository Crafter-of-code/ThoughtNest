package com.ThoughtNest.BlogService.controllers;

import com.ThoughtNest.BlogService.dto.AdditonaBlogOptionDto.BlogLikeRequestDto;
import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import com.ThoughtNest.BlogService.service.AdditonalBlogOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class AdditionalBlogOptionController {
    private final AdditonalBlogOptionService additonalBlogOptionService;

    /**/
    /*Like and dislike both in single controller*/
    @PostMapping("/like")
    public ResponseEntity<ResponseDto> blogLikeController(@RequestHeader("Authorization") String token
            ,@RequestBody  BlogLikeRequestDto requestData){
        if(requestData.getBlogId() == null || requestData.getPublicId() == null){
            System.out.println(requestData.getBlogId());
            System.out.println(requestData.getPublicId());
            throw  new IllegalArgumentException("unable to find public id or blog id in your request");
        }
        ResponseDto responseDto = additonalBlogOptionService.blogLikeService(token, requestData);
        return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
