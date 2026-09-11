package com.ThoughtNest.UserService.dto.auth;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SigninResponseDto {
    private boolean status;
    private String message;
}
