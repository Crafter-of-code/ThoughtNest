package com.ThoughtNest.BlogService.entity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Document(collection = "blogs")
public class BlogEntity {

    @Id
    private String blogId;

    // author
    private Long userId;
    private String userName;
    private String userEmail;

    // blog
    private String blogTitle;
    private String blogSummary;
    private String blogContent;
    // interaction
    private long blogViews;
    private Long blogLikes;
    private long blogComment;
    private long blogInteractionScore;
    // cover image
    private String coverImage;

    // creation time
    @CreatedDate
    private LocalDateTime createdAt;
}