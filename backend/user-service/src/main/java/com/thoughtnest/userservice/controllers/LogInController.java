package com.thoughtnest.userservice.controllers;

import com.thoughtnest.userservice.requestDto.LoginRequestDto;
import com.thoughtnest.userservice.responseDto.ResponseDto;
import com.thoughtnest.userservice.services.LogInService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor
public class LogInController {
    private LogInService logInService;
    public ResponseEntity<ResponseDto> LogInUser(@RequestBody LoginRequestDto userData){
        ResponseDto responseDto = logInService.logInUser(userData);
        if(responseDto.isStatus()){
           return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }else{
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDto);
        }
    }
}
