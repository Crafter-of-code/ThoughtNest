package com.ThoughtNest.Auth_Service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SigninRequestDto {
    private String userFirstName;
    private String userMiddleName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
}
