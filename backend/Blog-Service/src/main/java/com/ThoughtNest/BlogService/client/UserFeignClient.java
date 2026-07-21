package com.ThoughtNest.BlogService.client;

import com.ThoughtNest.BlogService.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.cloud.openfeign.FeignClient(name = "USER-SERVICE",configuration = FeignClientConfig.class)
public interface UserFeignClient {
    @PostMapping("/ask/user")
    public UserDto  getUserDetail(String token);
}
