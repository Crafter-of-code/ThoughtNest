package com.ThoughtNest.BlogService.dto.blog;

import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import lombok.*;

import java.util.List;
import java.util.UUID;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowingTopThreeBlogDto {
    private String userName;
    private UUID userPublicId;
    private String userPublicImageUrl;
    private List<ShortBlogResponseDto> shortBlogList;
}
