package com.ThoughtNest.Auth_Service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Data
@Setter
@Getter
public class LoginResponseDto {
    private boolean status;
    private String message;
    private String token;
    private UUID publicId;
}
