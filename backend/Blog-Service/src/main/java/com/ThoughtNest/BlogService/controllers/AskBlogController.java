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
    public ResponseEntity<Long> getCountOfUserBlogController(@RequestHeader("Authorization") String token
            ,@RequestParam("userId") Long userId){
        System.out.println("under blog ask count");
        System.out.println(userId);
        System.out.println(token);
        long count = askBlogService.blogCountService(userId);
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
