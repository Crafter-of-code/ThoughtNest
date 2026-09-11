package com.ThoughtNest.UserService.controllers;

import com.ThoughtNest.UserService.dto.PatchUserDetailRequestDto;
import com.ThoughtNest.UserService.dto.ResponseDto;
import com.ThoughtNest.UserService.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        ResponseDto responseDto = userService.getOwnDetailsService(token);
        return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @PatchMapping("/user")
    public ResponseEntity<ResponseDto> patchUpdatedUserDataController(@RequestHeader("Authorization") String token
            ,@ModelAttribute PatchUserDetailRequestDto updatedUserDetail){
        try{
            ResponseDto responseDto = userService.updateOwnerDetail(token.substring(7),updatedUserDetail);
            if(responseDto.isStatus()){
                return  ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
            }else{
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            ResponseDto responseDto = new ResponseDto();
            responseDto.setStatus(false);
            responseDto.setMessage("We are facing some problem while uploading you image to our server");
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body(responseDto);
        }

    }
    @DeleteMapping("/user")
    public ResponseEntity<ResponseDto> deleteOwnerAccount(@RequestHeader("Authorization") String token){
        System.out.println("deleteOwnerAccount controller start running");
        ResponseDto resposneDto = userService.deleteOwnerAccount(token);
        if(resposneDto.isStatus())return ResponseEntity.status(HttpStatus.OK).body(resposneDto);
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposneDto);
    }

}
