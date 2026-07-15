package com.ThoughtNest.UserService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SigninRepsonseDto {
    private boolean status;
    private String message;
}
