package com.ThoughtNest.UserService.controllers;
import com.ThoughtNest.UserService.dto.BlogAskDto;
import com.ThoughtNest.UserService.service.UserAskService;
import com.ThoughtNest.UserService.dto.UserAskDto;
import com.ThoughtNest.UserService.service.UserInteractionService;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ask")
@AllArgsConstructor
public class UserAskController {
    private UserAskService userAskService;
    private UserInteractionService userInteractionService;
    @PostMapping("/user")
    public ResponseEntity<UserAskDto> getUserInfoForService(@RequestHeader("Authorization") String token){
        if(token != ""){
            UserAskDto userAskDto =  userAskService.getOwnerInfo(token);
            return  ResponseEntity.status(HttpStatus.OK).body(userAskDto);
        }else{
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new UserAskDto());
        }
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserAskDto> getUserDetailByPublicId(@RequestHeader("Authorization") String token,
                                                              @PathVariable long id){
        UserAskDto userAskDto = userAskService.getUserInfo(id);
        if(userAskDto.getUserName() != null && !userAskDto.getUserName().equals("")){
            return  ResponseEntity.status(HttpStatus.OK).body(userAskDto);
        }else{
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(userAskDto);
        }
    }
    @PostMapping("/user/blog/published")
    public ResponseEntity<Boolean> userPublishesBlog(@RequestHeader("Authorization")String token,
                                                     @RequestBody BlogAskDto blogAskDto){
        boolean data = userInteractionService.userPublishedBlog(token,blogAskDto);
        if(data) return  ResponseEntity.status(HttpStatus.OK).body(true);
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }
}
