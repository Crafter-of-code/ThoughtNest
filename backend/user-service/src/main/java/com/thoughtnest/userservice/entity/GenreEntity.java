package com.thoughtnest.userservice.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Genre")
@Data
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;
    @Column(name = "genre",unique = true)
    private String genreName;
}
