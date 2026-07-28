package com.ThoughtNest.BlogService.dto;

import lombok.Data;
import org.springframework.scheduling.quartz.LocalDataSourceJobStore;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ShortBlogResponseDto {
    private String blogId;
    private String blogTitle;
    private String blogContent;
    private UUID publicId;
    private String userName;
    private LocalDateTime createdAt;
}
