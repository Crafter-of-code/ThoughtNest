package com.ThoughtNest.UserService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_profile")
@Getter
@Setter
@NoArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long profileId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private UserEntity userEntity;
    @Column(name = "user_location", length = 255)
    private String userLocation;
    @Column(name = "user_bio", length = 500)
    private String userBio;
    @Column(name = "user_published", nullable = false)
    private Long userPublished = 0L;
    @Column(name = "profile_view", nullable = false)
    private Long userProfileView = 0L;
    @Column(name = "total_likes", nullable = false)
    private Long userTotalLikes = 0L;
    @Column(name = "user_image_url", length = 500)
    private String userImageUrl;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_topics",
            joinColumns = @JoinColumn(name = "profile_id")
    )
    @Column(name = "topic")
    private List<String> userTopic = new ArrayList<>();
}