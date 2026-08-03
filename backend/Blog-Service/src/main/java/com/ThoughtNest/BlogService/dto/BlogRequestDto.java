package com.ThoughtNest.BlogService.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
public class BlogRequestDto {
    private String blogTitle;
    private String blogContent;
    private MultipartFile coverImage;
    private UUID userPublicId;
}
