package com.ThoughtNest.UserService.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class CompleteUserDetailDto {
    private UUID publicId;
    private String userName;
    private Long noOfFollowing;
    private Long noOfFollower;
    private Long noOfBlog;
    private LocalDateTime createAt;
    private UserProfileDto userProfile;
    @Getter
    @Setter
    public static class UserProfileDto {
        private String userBio;
        private String userLocation;
        private String userImageUrl;
        private Long userTotalLike;
        private Long userProfileView;
        private Long userPublished;
    }
}