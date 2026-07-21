package com.ThoughtNest.UserService.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity(name = "GenreDetail")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;
    @Column(name = "GenreName")
    private String genreName;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;
    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;
}
