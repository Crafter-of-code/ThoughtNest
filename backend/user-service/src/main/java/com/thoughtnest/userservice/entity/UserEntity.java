package com.ThoughtNest.UserService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "User")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "FirstName")
    private String userFirstName;

    @Column(name = "MiddleName")
    private String userMiddleName;

    @Column(name = "LastName")
    private String userLastName;

    @Column(name = "Email", unique = true)
    private String userEmail;

    @Column(name = "Password")
    private String userPassword;

    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}