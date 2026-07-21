package com.ThoughtNest.UserService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @GetMapping("/user")
    public ResponseEntity<Map<String,String>> userRouteTester(){
        Map<String,String> userRouteTesterMap = new HashMap<String,String>();
        userRouteTesterMap.put("status","true");
        userRouteTesterMap.put("message","Testing route is working successfully");
        return  ResponseEntity.status(HttpStatus.OK).body(userRouteTesterMap);
    }

}
