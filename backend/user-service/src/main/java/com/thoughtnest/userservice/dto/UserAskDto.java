package com.ThoughtNest.UserService.dto;

import lombok.Data;

@Data
public class UserAskDto {
    private Long userId;
    private String userName;
    private String userEmail;
}
