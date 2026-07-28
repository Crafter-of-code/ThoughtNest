package com.ThoughtNest.UserService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "user_image_public_url")
    private String userImagePublicUrl;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_topics",
            joinColumns = @JoinColumn(name = "profile_id")
    )
    @Column(name = "topic")
    private Set<String> userTopic = new HashSet<>();
}