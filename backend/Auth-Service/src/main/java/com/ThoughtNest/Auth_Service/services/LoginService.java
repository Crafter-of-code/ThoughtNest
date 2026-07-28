package com.ThoughtNest.Auth_Service.services;

import com.ThoughtNest.Auth_Service.clientConfig.AuthFeignClient;
import com.ThoughtNest.Auth_Service.dto.LoginRequestDto;
import com.ThoughtNest.Auth_Service.dto.LoginResponseDto;
import com.ThoughtNest.Auth_Service.utility.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private AuthFeignClient authFeignClient;
    private PasswordEncoder passwordEncoder;
    private JwtUtility jwtUtility;
    public LoginResponseDto getLogin(){
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setStatus(true);
        loginResponseDto.setMessage("This is the Get request on login controller");
        return  loginResponseDto;
    }
    public LoginResponseDto postLogin(LoginRequestDto loginUserData){
        System.out.println("Request is reached at login controller");
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        LoginRequestDto userCredentialDetail = authFeignClient.loginUser(loginUserData);
        if(passwordEncoder.matches(loginUserData.getUserPassword()
                ,userCredentialDetail.getUserPassword())) {
            loginResponseDto.setStatus(true);
            loginResponseDto.setMessage("Welcome");
            String jwtToken = jwtUtility.getToken(userCredentialDetail.getUserEmail());
            loginResponseDto.setToken(jwtToken);
            loginResponseDto.setPublicId(userCredentialDetail.getPublicId());
            System.out.println(loginResponseDto.getPublicId());
            System.out.println("Every thing looks great from login service");
        }else{
            loginResponseDto.setStatus(false);
            loginResponseDto.setMessage("Please check you credential again");
        }
        return loginResponseDto;
    }
}
