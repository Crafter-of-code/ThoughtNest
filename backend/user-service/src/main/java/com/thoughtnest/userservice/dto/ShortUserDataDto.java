package com.ThoughtNest.UserService.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ShortUserDataDto {
    private String userName;
    private UUID publicId;
    private String userProfileImage = "";
}
