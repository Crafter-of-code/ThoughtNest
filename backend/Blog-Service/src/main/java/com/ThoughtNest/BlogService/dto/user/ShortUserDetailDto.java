package com.ThoughtNest.BlogService.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ShortUserDetailDto {
    private String userName;
    private UUID publicId;
    private String userProfileImage = "";
}
