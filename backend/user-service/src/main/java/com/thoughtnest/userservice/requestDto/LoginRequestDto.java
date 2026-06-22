package com.thoughtnest.userservice.requestDto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String userEmail;
    private String userPassword;
}
