package com.ThoughtNest.BlogService.client;

import com.ThoughtNest.BlogService.dto.BlogDto;
import com.ThoughtNest.BlogService.dto.UserDto;
import org.springframework.web.bind.annotation.*;

@org.springframework.cloud.openfeign.FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {
    @PostMapping("/ask/user")
    public UserDto  getOwnerDetail(@RequestHeader("Authorization") String token);
    @GetMapping("/ask/user/{id}")
    public UserDto getUserDetailByUserId(@RequestHeader("Authorization") String token
    ,@PathVariable long id);
    @PostMapping("/ask/user/blog/published")
    public boolean whenUserPublishBlog(@RequestHeader("Authorization") String token, @RequestBody BlogDto blogData);
}
