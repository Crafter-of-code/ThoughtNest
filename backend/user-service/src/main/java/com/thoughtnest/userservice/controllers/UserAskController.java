package com.ThoughtNest.UserService.controllers;

import com.ThoughtNest.UserService.dto.UserAskDto;
import com.ThoughtNest.UserService.service.UserAskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ask")
@AllArgsConstructor
public class UserAskController {
    private UserAskService userAskService;
    @PostMapping("/user")
    public ResponseEntity<UserAskDto> getUserInfoForService(@RequestBody String token){
        UserAskDto userAskDto = new UserAskDto();
        if(token != ""){
          userAskDto =  userAskService.getUserInfo(token);
            return  ResponseEntity.status(HttpStatus.OK).body(userAskDto);
        }else{
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userAskDto);
        }
    }

}
