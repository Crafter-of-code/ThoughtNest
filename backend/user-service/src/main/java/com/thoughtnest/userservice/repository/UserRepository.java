package com.ThoughtNest.UserService.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ThoughtNest.UserService.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    boolean existsByUserEmail(String userEmail);
    Optional<UserEntity> findByUserEmail(String userEmail);
    Optional<List<UserEntity>> findByUserNameContainingIgnoreCase(String userName);
    Optional<UserEntity> findByPublicId(UUID id);
    Optional<Boolean> deleteByUserEmail(String id);
}
