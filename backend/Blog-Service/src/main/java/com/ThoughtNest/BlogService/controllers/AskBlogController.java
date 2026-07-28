package com.ThoughtNest.BlogService.controllers;

import com.ThoughtNest.BlogService.service.AskBlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ask")
@AllArgsConstructor
public class AskBlogController {
    private AskBlogService askBlogService;
    @GetMapping("/blog/count")
    public ResponseEntity<Object> getCountOfUserBlogController(@RequestHeader("Authorization") String token
            ,@RequestParam("userId") Long userId){
        System.out.println(token);
        Long count = askBlogService.blogCountService(userId);
        return  ResponseEntity.status(HttpStatus.OK).body(count);
    }
    /*
    to delete mutiple blog of a user
     */
    @DeleteMapping("/blogs")
    public ResponseEntity<Boolean> deleteUserAllBlogsController(@RequestHeader("Authorization") String token){
        return ResponseEntity.status(HttpStatus.OK).body(true);
    }
}
