package com.ThoughtNest.UserService.dto;

import com.ThoughtNest.UserService.entity.UserProfile;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
public class OwnerDataDto {
    private Long userId;
    private UUID publicId;
    private String userEmail;
    private String userName;
    private Long noOfFollower;
    private Long noOfFollowing;
    private LocalDateTime createdAt;
    private UserProfileDto userProfile;
    @Data
    @Getter
    @Setter
    public static class UserProfileDto{
        private String userLocation;
        private String userBio;
        private Long userPublished = 0L;
        private Long userProfileView = 0L;
        private Long userTotalLikes = 0L;
        private String userImageUrl;
        private Set<String> userTopics;
    }
}
