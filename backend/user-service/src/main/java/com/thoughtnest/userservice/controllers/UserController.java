package com.thoughtnest.userservice.controllers;

import com.thoughtnest.userservice.requestDto.LoginRequestDto;
import com.thoughtnest.userservice.responseDto.ResponseDto;
import com.thoughtnest.userservice.entity.UserEntity;
import com.thoughtnest.userservice.respository.UserRepository;
import com.thoughtnest.userservice.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private UserRepository userRepository;
    private UserService userService;
}
