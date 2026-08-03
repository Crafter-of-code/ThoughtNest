package com.ThoughtNest.UserService.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/*
configuration = FiegnClientConfig.class
 */
@FeignClient(name = "BLOG-SERVICE")
public interface BlogFiegnClient {
    @GetMapping("/ask/blog/count")
    public Long CountByUserId(@RequestHeader("Authorization") String token,@RequestParam("userId") Long userId);
    @DeleteMapping("/ask/blogs")
    public boolean deleteOwnerBlog(@RequestHeader("Authorization") String token);
}
