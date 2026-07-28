package com.ThoughtNest.UserService.repository;
import com.ThoughtNest.UserService.dto.ShortUserDataDto;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ThoughtNest.UserService.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUserEmail(String userEmail);
    Optional<List<UserEntity>> findByUserNameContainingIgnoreCase(String userName);
    Optional<UserEntity> findByPublicId(UUID id);
}
