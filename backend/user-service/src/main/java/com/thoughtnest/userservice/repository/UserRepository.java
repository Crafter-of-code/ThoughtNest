package com.ThoughtNest.UserService.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ThoughtNest.UserService.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserEmail(String userEmail);
}
