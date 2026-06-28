package com.thoughtnest.userservice.controllers;

import com.thoughtnest.userservice.entity.UserEntity;
import com.thoughtnest.userservice.responseDto.ResponseDto;
import com.thoughtnest.userservice.services.SignInService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/signin")
@AllArgsConstructor
public class SignInController {
    private SignInService signInService;
    @PostMapping
    public ResponseEntity<ResponseDto> registerNewUser(@RequestBody UserEntity userData){
        ResponseDto responseDto = signInService.registerNewUser(userData);
        System.out.println(userData.getUserFirstName());

        if(responseDto.isStatus()){
//            Cookie cookie = new Cookie("_uEmail",userData.getUserEmail());
//            cookie.setPath("/");
//            cookie.setMaxAge(60 * 60 * 24); // 1 day
//            cookie.setHttpOnly(true);
//            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        }
        else{
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }
}
