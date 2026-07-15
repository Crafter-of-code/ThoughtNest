package com.ThoughtNest.APIGateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DefaultController {
    @GetMapping
    public ResponseEntity<String> serverIsRunning(){
        return ResponseEntity.status(HttpStatus.OK).body("Gate Way is running on port"+"8080");
    }

}
