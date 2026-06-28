package com.thoughtnest.userservice.services;

import com.thoughtnest.userservice.entity.UserEntity;
import com.thoughtnest.userservice.requestDto.LoginRequestDto;
import com.thoughtnest.userservice.responseDto.ResponseDto;
import com.thoughtnest.userservice.respository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogInService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ResponseDto responseDto;
    public ResponseDto logInUser(LoginRequestDto userData){
        try{
            UserEntity userDataFromDataBase = userRepository
                    .findByUserEmail(userData.getUserEmail()).orElseThrow(()->
                            new RuntimeException("User Not Found"));
            boolean isPasswordCorrect = passwordEncoder
                    .matches(userData.getUserPassword()
                            ,userDataFromDataBase.getUserPassword());
            if(isPasswordCorrect){
                responseDto.setStatus(true);
                responseDto.setMessage("Welcome");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            responseDto.setStatus(false);
            responseDto.setMessage("We are facing some problem while login you in");
        }
        return responseDto;
    }
}
