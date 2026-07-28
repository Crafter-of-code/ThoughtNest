package com.ThoughtNest.UserService.service;

import com.ThoughtNest.UserService.Utility.JwtUtil;
import com.ThoughtNest.UserService.dto.UserAskDto;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserAskService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    public UserAskDto getUserInfo(String token){
        UserAskDto userAskDto = new UserAskDto();
        String subString = token.substring(7);
        Claims userData = jwtUtil.getClaims(subString);
        try{
            Optional<UserEntity> userDetail = userRepository.findByUserEmail(userData.getSubject());
            userAskDto.setUserId(userDetail.get().getUserId());
            userAskDto.setUserName(userDetail.get().getUserName());
            userAskDto.setUserEmail(userDetail.get().getUserEmail());
            userAskDto.setPublicId(userDetail.get().getPublicId());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return  userAskDto;
    }
}
