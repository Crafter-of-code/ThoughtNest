package com.ThoughtNest.UserService.repository;

import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow,Long> {
    boolean existsByFollowerAndFollowing(UserEntity follower, UserEntity following);
    Optional<UserFollow> findByFollowerAndFollowing(UserEntity follower, UserEntity following);
}
