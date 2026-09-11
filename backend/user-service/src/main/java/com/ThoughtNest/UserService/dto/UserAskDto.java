package com.ThoughtNest.UserService.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserAskDto {
    private Long userId;
    private String userName;
    private String userEmail;
    private UUID publicId;
    private String userImageUrl;
}
