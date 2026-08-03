package com.ThoughtNest.UserService.service;

import com.ThoughtNest.UserService.Utility.JwtUtil;
import com.ThoughtNest.UserService.dto.UserAskDto;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAskService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    public UserAskDto getOwnerInfo(String token){
        UserAskDto userAskDto = new UserAskDto();
        String subString = token.substring(7);
        Claims userData = jwtUtil.getClaims(subString);
        try{
            Optional<UserEntity> userDetail = userRepository.findByUserEmail(userData.getSubject());
            userAskDto.setUserId(userDetail.get().getUserId());
            userAskDto.setUserName(userDetail.get().getUserName());
            userAskDto.setUserEmail(userDetail.get().getUserEmail());
            userAskDto.setPublicId(userDetail.get().getPublicId());
            userAskDto.setUserImageUrl(null);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  userAskDto;
    }
    public UserAskDto getUserInfo(long id){
        UserAskDto userAskDto = new UserAskDto();
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(userEntity.isPresent()){
            userAskDto.setUserId(null);
            userAskDto.setPublicId(userEntity.get().getPublicId());
            userAskDto.setUserName(userEntity.get().getUserName());
            userAskDto.setUserImageUrl(userEntity.get().getUserProfile().getUserImageUrl());
        }
        return  userAskDto;
    }
}
//eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1emFpckBnbWFpbC5jb20iLCJpYXQiOjE3ODUzOTYzNjF9.orREDTG04Fu9H6BcItZZT0kNMjcfIa8XsGdLd82N_Qo