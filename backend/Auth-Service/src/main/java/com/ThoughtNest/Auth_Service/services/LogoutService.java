package com.ThoughtNest.Auth_Service.services;

import com.ThoughtNest.Auth_Service.clientConfig.AuthFeignClient;
import com.ThoughtNest.Auth_Service.dto.LogoutResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LogoutService {
    private AuthFeignClient authFeignClient;
    public LogoutResponseDto logoutUserService(){
        return  authFeignClient.logoutUser();
    }
}
