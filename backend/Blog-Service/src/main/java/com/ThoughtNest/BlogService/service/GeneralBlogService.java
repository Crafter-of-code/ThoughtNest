package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GeneralBlogService {
    private final BlogRepository blogRepository;
    public List<ShortBlogResponseDto> getGeneralBlogs(){
//        BlogEntity blogEntity = blogRepository.
        return null;
    }
}
