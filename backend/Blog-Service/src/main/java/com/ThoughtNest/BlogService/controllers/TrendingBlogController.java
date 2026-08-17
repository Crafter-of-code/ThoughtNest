package com.ThoughtNest.BlogService.controllers;

import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import com.ThoughtNest.BlogService.service.TrendingBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blogs/trending")
@RequiredArgsConstructor
public class TrendingBlogController {
    public final TrendingBlogService trendingBlogService;
    @GetMapping
    public ResponseEntity<ResponseDto<List<ShortBlogResponseDto>>> getTrendingBlogController(){
        ResponseDto<List<ShortBlogResponseDto>> responseDto = trendingBlogService.getTrendingBlogService();
        return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
