package com.thoughtnest.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name="users_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private UUID userId;
    @Column(name="FirstName",nullable = false)
    private String userFirstName;
    @Column(name="MiddleName",nullable = false)
    private String userMiddleName;
    @Column(name="LastName",nullable = false)
    private String userLastName;
    @Column(nullable = false,unique = true,name = "Email")
    private String userEmail;
    @Column(name="Password")
    private String userPassword;
}
