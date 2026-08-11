package com.ThoughtNest.BlogService.controllers;

import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import com.ThoughtNest.BlogService.dto.blog.FollowingTopThreeBlogDto;
import com.ThoughtNest.BlogService.service.FollowingBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@RequiredArgsConstructor
public class FollowingBlogController {
    private final FollowingBlogService followingBlogService;
    @GetMapping("/following")
    public ResponseEntity<ResponseDto<List<FollowingTopThreeBlogDto>>> getFollowingBlogController
            (@RequestHeader("Authorization")String token){
        System.out.println("YOu are requesting on following blog");
        ResponseDto<List<FollowingTopThreeBlogDto>> responseDto = followingBlogService.getFollowingBlogService(token);
        return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
