package com.thoughtnest.userservice.controllers;

import com.thoughtnest.userservice.DTO.ResponseDto;
import com.thoughtnest.userservice.entity.UserEntity;
import com.thoughtnest.userservice.respository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private UserRepository userRepository;
    private ResponseDto responseDto;
    @GetMapping
    public String testingUserRoute(){
        return "the server is running";
    }
    @PostMapping()
    public ResponseEntity<ResponseDto> registerUser(@RequestBody UserEntity data) {
        try {
            userRepository.save(data);
            responseDto.setStatus(true);
            responseDto.setMessage("Signed in Successfully");
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            responseDto.setStatus(false);
            responseDto.setMessage("We are facing some problem");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
        }

    }
}
