package com.ThoughtNest.Auth_Service.controllers;

import com.ThoughtNest.Auth_Service.dto.SigninRequestDto;
import com.ThoughtNest.Auth_Service.dto.SigninResponseDto;
import com.ThoughtNest.Auth_Service.services.SigninService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/signin")
@AllArgsConstructor
public class SigninController {
    private SigninService signinService;
    @PostMapping
    public ResponseEntity<SigninResponseDto> createUserAccount(@RequestBody SigninRequestDto userData){
        System.out.println("Request reaches to signin controller");
        SigninResponseDto signinResponseDto = signinService.createUserAccount(userData);
        if(signinResponseDto.isStatus()){
            return ResponseEntity.status(HttpStatus.CREATED).body(signinResponseDto);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(signinResponseDto);
        }
    }
}
