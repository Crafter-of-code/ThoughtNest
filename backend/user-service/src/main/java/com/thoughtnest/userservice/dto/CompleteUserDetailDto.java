package com.ThoughtNest.UserService.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private boolean following = false;
    @Getter
    @Setter
    public static class UserProfileDto {
        private String userBio;
        private String userLocation;
        private String userImageUrl;
        private Long userTotalLike;
        private Long userProfileView;
        private Long userPublished;
        private Set<String> userTopics;
        private LocalDateTime userJoinedOn;
    }
}