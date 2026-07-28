package com.ThoughtNest.UserService.controllers;

import com.ThoughtNest.UserService.dto.ResponseDto;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.service.UserService;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    /*
    Request param variable get by user name
     */
    @GetMapping("/user")
    public ResponseEntity<ResponseDto> getUserByUserNameController(@RequestParam("userName")String userName){
        ResponseDto responseDto = userService.getUserByUserNameController(userName);
        if(responseDto.isStatus()){
            return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
        }
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseDto> getUserDetailController(@RequestHeader("Authorization") String token
            ,@PathVariable("id") UUID id){
        ResponseDto responseDto = userService.getUserDetailService(token,id);
        if(responseDto.isStatus()){
            return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
    }
    @GetMapping("/user/me")
    public ResponseEntity<ResponseDto> getOwnDetailsController(@RequestHeader("Authorization") String token){
        ResponseDto responseDto = userService.getOwnDetailsService(token.substring(7));
        return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

}
