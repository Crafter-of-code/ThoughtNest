package com.ThoughtNest.BlogService.client;

import com.ThoughtNest.BlogService.dto.BlogDto;
import com.ThoughtNest.BlogService.dto.UserDto;
import com.ThoughtNest.BlogService.dto.user.ShortUserDetailDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.cloud.openfeign.FeignClient(name = "USER-SERVICE")
public interface UserFeignClient {
    @PostMapping("/ask/user")
    public UserDto  getOwnerDetail(@RequestHeader("Authorization") String token);
    @GetMapping("/ask/user/{id}")
    public UserDto getUserDetailByUserId(@RequestHeader("Authorization") String token
    ,@PathVariable long id);
    @PostMapping("/ask/user/blog/published")
    public boolean whenUserPublishBlog(@RequestHeader("Authorization") String token, @RequestBody BlogDto blogData);
    @GetMapping("/ask/user/follower")
    public List<ShortUserDetailDto> getUserFollowings(@RequestHeader("Authorization") String token);
}
