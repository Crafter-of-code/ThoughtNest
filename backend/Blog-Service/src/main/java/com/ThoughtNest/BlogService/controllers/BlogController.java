package com.ThoughtNest.BlogService.controllers;

import com.ThoughtNest.BlogService.dto.BlogRequestDto;
import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.service.BlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ThoughtNest.BlogService.dto.ResponseDto;

import java.io.IOException;
import java.util.List;

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
    @PostMapping("/blog/like")
    public ResponseEntity<ResponseDto> blogLikeController(){
        return  null;
    }
    @DeleteMapping("/blog/{id}")
    public ResponseEntity<ResponseDto> blogDeleteControler(@RequestHeader("Authorization") String token,
                                                           @PathVariable("id") String id){
        ResponseDto responseDto =  blogService.blogDeleteService(token.substring(7),id);
        if(responseDto.isStatus()){
             return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }else{
            if(responseDto.getMessage() =="you are unauthrized to delete it"){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }

    }
    @GetMapping("/blog/me")
    public ResponseEntity<ResponseDto> getMy3BlogController(@RequestHeader("Authorization") String token){
        com.ThoughtNest.BlogService.dto.ResponseDto responseDto = blogService.getMy3BlogService(token.substring(7));
        if(responseDto.isStatus()){
            return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }else{

            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }
    }

}
