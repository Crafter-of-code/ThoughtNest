package com.ThoughtNest.UserService.dto.auth;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class SignupRequestDto {

    @NotBlank
    private String userName;

    @Email
    @NotBlank
    private String userEmail;

    @NotBlank
    private String userPassword;
}
