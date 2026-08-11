package com.ThoughtNest.UserService.repository;

import com.ThoughtNest.UserService.dto.ShortUserDataDto;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.entity.UserFollow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow,Long> {
    boolean existsByFollowerAndFollowing(UserEntity follower, UserEntity following);
    Optional<UserFollow> findByFollowerAndFollowing(UserEntity follower, UserEntity following);
    @Query("""
    SELECT new com.ThoughtNest.UserService.dto.ShortUserDataDto(
    f.userName,
    f.publicId,
    p.userImageUrl
    )
    FROM UserFollow uf
    JOIN uf.following f
    LEFT JOIN f.userProfile p
    WHERE uf.follower.userId = :followerId
""")
List<ShortUserDataDto> findAllFollowingByFollowerId(
        @Param("followerId") Long followerId
);
}
