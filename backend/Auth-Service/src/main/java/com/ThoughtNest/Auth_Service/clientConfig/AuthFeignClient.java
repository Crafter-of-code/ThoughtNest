package com.ThoughtNest.Auth_Service.clientConfig;

import com.ThoughtNest.Auth_Service.configurations.FeignConfig;
import com.ThoughtNest.Auth_Service.dto.LoginRequestDto;
import com.ThoughtNest.Auth_Service.dto.LoginResponseDto;
import com.ThoughtNest.Auth_Service.dto.SigninRequestDto;
import com.ThoughtNest.Auth_Service.dto.SigninResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "USER-SERVICE",configuration = FeignConfig.class)
public interface AuthFeignClient {
    @PostMapping("/register")
    public SigninResponseDto registerUser(SigninRequestDto userData);
    @PostMapping("/login")
    public LoginRequestDto loginUser(LoginRequestDto userData);
}
