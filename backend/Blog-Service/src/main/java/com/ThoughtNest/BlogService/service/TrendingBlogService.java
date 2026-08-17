package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrendingBlogService {
    private final BlogRepository blogRepository;
    public ResponseDto<List<ShortBlogResponseDto>> getTrendingBlogService(){
        ResponseDto<List<ShortBlogResponseDto>> responseDto = new ResponseDto<List<ShortBlogResponseDto>>();
        List<ShortBlogResponseDto> shortBlogResponseDtos = blogRepository.findTop20ByOrderByBlogInteractionScoreDesc().stream()
                .map(blog-> new ShortBlogResponseDto(
                    blog.getBlogId(),
                        blog.getBlogTitle(),
                        blog.getBlogContent(),
                        blog.getCreatedAt(),
                        blog.getUserPublicId(),
                        blog.getUserName(),
                        blog.getUserImageUrl()
                )).toList();
        System.out.println(shortBlogResponseDtos);
        responseDto.setStatus(true);
        responseDto.setMessage("Fetched data successfully");
        responseDto.setData(shortBlogResponseDtos);
        return  responseDto;
    }
}
