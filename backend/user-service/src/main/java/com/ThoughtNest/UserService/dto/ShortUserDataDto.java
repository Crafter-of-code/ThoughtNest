package com.ThoughtNest.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ShortUserDataDto {
    private String userName;
    private UUID publicId;
    private String userProfileImage = "";
}
