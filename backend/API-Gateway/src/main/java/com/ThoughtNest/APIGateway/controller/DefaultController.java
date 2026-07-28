package com.ThoughtNest.APIGateway.controller;

import jakarta.ws.rs.core.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class DefaultController {
    @GetMapping
    public ResponseEntity<Map<String,Object>> serverIsRunning(){
        Map<String,Object>  responseData = new HashMap<String,Object>();
        responseData.put("status","true");
        responseData.put("message","server is running properly");
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

}
