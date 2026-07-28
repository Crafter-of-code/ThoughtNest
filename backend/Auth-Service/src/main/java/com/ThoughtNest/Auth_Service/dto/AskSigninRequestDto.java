package com.ThoughtNest.Auth_Service.dto;

import lombok.Data;

@Data
public class AskSigninRequestDto {
    private String userName;
    private String userEmail;
    private String userPassword;
}
