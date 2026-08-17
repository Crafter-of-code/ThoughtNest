package com.ThoughtNest.UserService.controllers;

import com.ThoughtNest.UserService.dto.ResponseDto;
import com.ThoughtNest.UserService.service.UserFollowerService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserFollowController {

    private final UserFollowerService userFollowerService;
    @PostMapping("/user/follow/{publicId}")
    public ResponseEntity<ResponseDto> setFollowerController(@RequestHeader("Authorization")String token
            , @PathVariable UUID publicId){
        System.out.println("public id is:" + publicId);
        ResponseDto responseDto = userFollowerService.setFollower(token,publicId);
        return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @DeleteMapping("/user/unfollow/{publicId}")
    public ResponseEntity<ResponseDto> removeFollowingController(@RequestHeader("Authorization")String token
            , @PathVariable UUID publicId){
        ResponseDto responseDto = userFollowerService.removeFollowingService(token,publicId);
        return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @DeleteMapping("/user/follower/{publicId}")
    public ResponseEntity<ResponseDto<Void>> removeFollowerController(
            @RequestHeader("Authorization") String token,
            @PathVariable UUID publicId,
            @RequestParam UUID ownerPublicId) {
        ResponseDto<Void> responseDto =
                userFollowerService.removeFollowerService(
                        token,
                        publicId,
                        ownerPublicId
                );
        return ResponseEntity.ok(responseDto);
    }
}
