package com.thoughtnest.userservice.controllers;

import com.thoughtnest.userservice.entity.UserEntity;
import com.thoughtnest.userservice.responseDto.ResponseDto;
import com.thoughtnest.userservice.services.SignInService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/SignIn")
@AllArgsConstructor
public class SignInController {
    private SignInService signInService;
    public ResponseEntity<ResponseDto> registerNewUser(@RequestBody UserEntity userData){
        ResponseDto responseDto = signInService.registerNewUser(userData);
        if(responseDto.isStatus()){
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }
        else{
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }
}
