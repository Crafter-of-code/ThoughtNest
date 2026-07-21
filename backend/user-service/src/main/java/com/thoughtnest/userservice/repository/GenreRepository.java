package com.ThoughtNest.UserService.repository;

import com.ThoughtNest.UserService.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<GenreEntity,Long> {
}
