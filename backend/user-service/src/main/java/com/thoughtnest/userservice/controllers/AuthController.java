package com.ThoughtNest.UserService.controllers;

import com.ThoughtNest.UserService.dto.LoginRequestDto;
import com.ThoughtNest.UserService.dto.LogoutResponseDto;
import com.ThoughtNest.UserService.dto.ResponseDto;
import com.ThoughtNest.UserService.dto.SigninRepsonseDto;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    @PostMapping("/register")
    public SigninRepsonseDto signinController(@RequestBody UserEntity userData){
        return  authService.signinService(userData);
    }
    @PostMapping("/login")
    public LoginRequestDto loginController(@RequestBody LoginRequestDto loginUserData){
        return  authService.loginService(loginUserData);
    }
    @GetMapping("/signout")
    public LogoutResponseDto logoutController(){
        return  authService.logoutService();
    }
}
