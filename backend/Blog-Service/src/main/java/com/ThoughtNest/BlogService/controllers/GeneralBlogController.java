package com.ThoughtNest.BlogService.controllers;

import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.service.GeneralBlogService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/blog")
@AllArgsConstructor
public class GeneralBlogController {
    private GeneralBlogService generalBlogService;
    @GetMapping("/general")
    public ResponseEntity<List<BlogEntity>> getGeneralBlog(){
        return  null;
    }
}
