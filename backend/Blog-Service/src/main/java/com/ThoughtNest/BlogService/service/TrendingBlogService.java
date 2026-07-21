package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TrendingBlogService {
    private BlogRepository blogRepository;
    public List<BlogEntity> getTrendingBlogService(){
        return  null;
    }
}
