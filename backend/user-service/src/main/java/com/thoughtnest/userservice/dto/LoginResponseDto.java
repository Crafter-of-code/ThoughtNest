package com.ThoughtNest.UserService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class LoginResponseDto {
    private boolean status;
    private String message;
    private String token;
}
