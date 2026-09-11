package com.ThoughtNest.UserService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Getter
@Setter
public class LoginResponseDto {
    private boolean status;
    private String message;
    private String token;
    private UUID publicId;
}
