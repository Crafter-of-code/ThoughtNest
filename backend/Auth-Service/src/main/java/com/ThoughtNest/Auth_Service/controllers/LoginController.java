package com.ThoughtNest.Auth_Service.controllers;

import com.ThoughtNest.Auth_Service.dto.LoginRequestDto;
import com.ThoughtNest.Auth_Service.dto.LoginResponseDto;
import com.ThoughtNest.Auth_Service.services.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/login")
@AllArgsConstructor
public class LoginController {
    private LoginService loginService;
    @GetMapping
    public ResponseEntity<LoginResponseDto> getLogin(){
        LoginResponseDto loginResponseDto = loginService.getLogin();
        if(loginResponseDto.isStatus()){
            return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginResponseDto);
        }
    }
    @PostMapping
    public ResponseEntity<LoginResponseDto> postLogin(@RequestBody LoginRequestDto userData){
        System.out.println("Request is reached at login controller");
        LoginResponseDto loginResponseDto = loginService.postLogin(userData);
        if(loginResponseDto.isStatus()){
            return ResponseEntity.status(HttpStatus.OK).body(loginResponseDto);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginResponseDto);
        }
    }
}
