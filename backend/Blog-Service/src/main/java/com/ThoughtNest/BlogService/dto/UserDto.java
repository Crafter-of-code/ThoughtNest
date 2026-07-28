package com.ThoughtNest.BlogService.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private Long userId;
    private UUID publicId;
    private String userName;
    private String userEmail;
}
