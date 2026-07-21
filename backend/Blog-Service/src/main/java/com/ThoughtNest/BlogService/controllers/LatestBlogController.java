package com.ThoughtNest.BlogService.controllers;

import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.service.LatestBlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/blog")
@AllArgsConstructor
public class LatestBlogController {
    private final LatestBlogService latestBlogService;
    @GetMapping("/latest")
    @SuppressWarnings("rawtypes")
    public ResponseEntity<ResponseDto>  getLatestBlogController(){
        ResponseDto responseDto = latestBlogService.getLatestBlog();
        if(Objects.equals(responseDto.getMessage(), "We are receiving problem communicating to our backend service")){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }
}
