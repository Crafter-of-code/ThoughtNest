package com.ThoughtNest.BlogService.entity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Data
@Document(collection = "blogs")
public class BlogEntity {

    @Id
    private String blogId;

    // author
    private Long userId;
    private UUID userPublicId;
    private String userName;
    private String userEmail;
    private String userImageUrl;
    // blog
    private String blogTitle;
    private String blogSummary;
    private String blogContent;
    // interaction
    private List<UUID> blogViews;
    private List<UUID> blogLikes;
    private List<Map<String,String>> blogComment;
    private long blogInteractionScore;
    // cover image
    private String coverImage;
    private String coverImagePublicUrl;

    // creation time
    @CreatedDate
    private LocalDateTime createdAt;
}