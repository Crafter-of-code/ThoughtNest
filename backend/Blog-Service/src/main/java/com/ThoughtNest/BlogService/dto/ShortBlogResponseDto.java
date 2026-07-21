package com.ThoughtNest.BlogService.dto;

import lombok.Data;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

import java.time.LocalDateTime;

@Data
public class ShortBlogResponseDto {
    private String blogId;
    private String blogTitle;
    private String blogContent;
    private String userId;
    private String userName;
    private LocalDateTime createdAt;
}
