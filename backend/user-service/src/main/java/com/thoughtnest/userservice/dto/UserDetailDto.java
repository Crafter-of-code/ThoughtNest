package com.ThoughtNest.UserService.dto;

import com.ThoughtNest.UserService.entity.UserProfile;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetailDto {
    private  Long userId;
    private String userName;
    private LocalDateTime createAt;
    private Integer noOfFollowing;
    private Integer noOfFollower;
    private UserProfile userProfile;
    private Long noOfBlog;
}
