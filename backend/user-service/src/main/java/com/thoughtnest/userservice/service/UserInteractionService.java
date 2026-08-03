package com.ThoughtNest.UserService.service;

import com.ThoughtNest.UserService.Utility.JwtUtil;
import com.ThoughtNest.UserService.dto.BlogAskDto;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserInteractionService {
    private UserRepository userRepository;
    private JwtUtil jwtUtil;
    public boolean userProfileView(){
        return  true;
    }
    public boolean userPublishedBlog(String token, BlogAskDto blogData){
        Optional<UserEntity> userData = userRepository.findByUserEmail(jwtUtil.getClaims
                (token.substring(7)).getSubject());
        if(userData.isEmpty()) return false;
        userData.get().getUserProfile().getUserPublished();
        return true;
    }
}
