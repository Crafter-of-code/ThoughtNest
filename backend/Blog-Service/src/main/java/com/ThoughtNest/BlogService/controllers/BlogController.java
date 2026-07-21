package com.ThoughtNest.BlogService.controllers;

import com.ThoughtNest.BlogService.dto.BlogRequestDto;
import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.service.BlogService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BlogController {
    private BlogService blogService;
    @PostMapping("/blog")
    public ResponseEntity<ResponseDto> PostBlogController(@ModelAttribute BlogRequestDto userBlog
            ,@RequestHeader("Authorization") String token) throws IOException {
        ResponseDto responseDto = blogService.uploadUserBlog(userBlog,token);
        if(responseDto.isStatus()){
            return  ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }
    @GetMapping("/blog")
    public ResponseEntity<ResponseDto> getSingleBlogController(@RequestParam String blogId){
        ResponseDto responseDto = blogService.getSingleBlogService(blogId);
        if(responseDto.isStatus()){
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }else{
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(responseDto);
        }
    }
}
