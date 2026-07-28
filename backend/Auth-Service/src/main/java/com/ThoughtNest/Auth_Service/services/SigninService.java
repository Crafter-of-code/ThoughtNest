package com.ThoughtNest.Auth_Service.services;

import com.ThoughtNest.Auth_Service.clientConfig.AuthFeignClient;
import com.ThoughtNest.Auth_Service.dto.AskSigninRequestDto;
import com.ThoughtNest.Auth_Service.dto.SigninRequestDto;
import com.ThoughtNest.Auth_Service.dto.SigninResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SigninService {
    private AuthFeignClient authFeignClient;
    private PasswordEncoder passwordEncoder;
    public SigninResponseDto createUserAccount(SigninRequestDto userData){
        AskSigninRequestDto askSigninRequestDto = new AskSigninRequestDto();
        String encryptedPassword = passwordEncoder.encode(userData.getUserPassword());
        askSigninRequestDto.setUserPassword(encryptedPassword);
        askSigninRequestDto.setUserName(userData.getUserFirstName()+" "+userData.getUserLastName());
        askSigninRequestDto.setUserEmail(userData.getUserEmail());
        SigninResponseDto signinResponseDto = authFeignClient.registerUser(askSigninRequestDto);
        if(signinResponseDto.isStatus()){
            System.out.println("login successfull");
             signinResponseDto.setStatus(true);
             signinResponseDto.setMessage("Account created successfully");
            return signinResponseDto;
        }else{
            signinResponseDto.setStatus(false);
            signinResponseDto.setMessage("Facing some problem while creating the account");
            return signinResponseDto;
        }
    }
}
