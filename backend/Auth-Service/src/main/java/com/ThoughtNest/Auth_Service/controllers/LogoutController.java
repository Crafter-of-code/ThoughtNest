package com.ThoughtNest.Auth_Service.controllers;

import com.ThoughtNest.Auth_Service.dto.LogoutResponseDto;
import com.ThoughtNest.Auth_Service.services.LogoutService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class LogoutController {
    private LogoutService logoutService;
    @GetMapping("/logout")
    public ResponseEntity<LogoutResponseDto> logoutController(){
        LogoutResponseDto logoutResponseDto = logoutService.logoutUserService();
        if(logoutResponseDto.isStatus()){
            return  ResponseEntity.status(HttpStatus.OK).body(logoutResponseDto);
        }else{
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(logoutResponseDto);
        }
    }
}
