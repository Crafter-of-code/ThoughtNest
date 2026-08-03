package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.dto.UserDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.HashSet;
@Component
@AllArgsConstructor
public class BlogIntractionService {
    private BlogRepository blogRepository;
    public boolean recordView(UserDto user, BlogEntity blogData){
        List<UUID> previousViewedData = blogData.getBlogViews();
        if(previousViewedData.contains(user.getPublicId())) return  true;
        previousViewedData.add(user.getPublicId());
        return  true;
    }
}
