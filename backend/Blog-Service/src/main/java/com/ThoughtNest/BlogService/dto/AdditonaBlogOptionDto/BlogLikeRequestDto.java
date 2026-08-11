package com.ThoughtNest.BlogService.dto.AdditonaBlogOptionDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Data
@Getter
@Setter
public class BlogLikeRequestDto {
    private UUID publicId;
    private String blogId;
}
