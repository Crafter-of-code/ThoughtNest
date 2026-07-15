package com.ThoughtNest.Auth_Service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginRequestDto {
    private String userEmail;
    private String userPassword;
}
