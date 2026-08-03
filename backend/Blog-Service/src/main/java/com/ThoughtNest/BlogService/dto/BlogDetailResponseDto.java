package com.ThoughtNest.BlogService.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BlogDetailResponseDto {

    // Blog
    private String blogId;
    private String blogTitle;
    private String blogSummary;
    private String blogContent;
    private String coverImage;

    // Author
    private String userName;
    private UUID userPublicId;
    private String userImageUrl;
    // Statistics
    private long blogViews;
    private long blogLikes;
    private long blogComments;

    // Publish Date
    private LocalDateTime createdAt;
}