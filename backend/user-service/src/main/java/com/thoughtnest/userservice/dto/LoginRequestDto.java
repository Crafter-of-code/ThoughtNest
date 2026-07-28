package com.ThoughtNest.UserService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class LoginRequestDto {
    private String userEmail;
    private String userPassword;
    private UUID publicId;
}
