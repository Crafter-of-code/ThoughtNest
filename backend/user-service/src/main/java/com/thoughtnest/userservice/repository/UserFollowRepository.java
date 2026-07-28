package com.ThoughtNest.UserService.repository;

import com.ThoughtNest.UserService.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow,Long> {
}
