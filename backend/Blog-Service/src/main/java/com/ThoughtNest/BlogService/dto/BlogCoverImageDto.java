package com.ThoughtNest.BlogService.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@Getter
@Setter
public class BlogCoverImageDto {
    public MultipartFile coverImage;
    public String secureUrl;
    public String publicUrl;
}
