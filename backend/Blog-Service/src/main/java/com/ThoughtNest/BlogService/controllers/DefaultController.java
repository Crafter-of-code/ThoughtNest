package com.ThoughtNest.BlogService.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class DefaultController {
    @GetMapping
    public List<Map<String,String>> defaultControllerForBlog(){
        Map<String,String> defaultUrlData = new HashMap<String,String>();
        List<Map<String,String>> myArr = new ArrayList<>();
        defaultUrlData.put("status","True");
        defaultUrlData.put("message","Requesting on base url of blog");
        myArr.add(defaultUrlData);
        return  myArr;
    }
}
