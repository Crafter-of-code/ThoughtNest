package com.ThoughtNest.UserService.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;

@Data
@Getter
@Setter
public class PatchUserDetailRequestDto {
    private MultipartFile userProfileData;
    private String userName;
    private String userBio;
    private String userLocation;
    private HashSet <String> userTopic;
}
