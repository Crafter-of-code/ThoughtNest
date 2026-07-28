package com.ThoughtNest.Auth_Service.clientConfig;

import com.ThoughtNest.Auth_Service.configurations.FeignConfig;
import com.ThoughtNest.Auth_Service.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "USER-SERVICE",configuration = FeignConfig.class)
public interface AuthFeignClient {
    @PostMapping("/register")
    public SigninResponseDto registerUser(AskSigninRequestDto userData);
    @PostMapping("/login")
    public LoginRequestDto loginUser(LoginRequestDto userData);
    @GetMapping("/signout")
    public LogoutResponseDto logoutUser();
}
