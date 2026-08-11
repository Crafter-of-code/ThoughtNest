package com.ThoughtNest.BlogService.repository;

import com.ThoughtNest.BlogService.dto.BlogDetailResponseDto;
import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogRepository extends MongoRepository<BlogEntity,String> {
    Optional<List<BlogEntity>> findByUserId(Long userId);
    Optional<List<BlogEntity>> findTop20ByOrderByCreatedAtDesc();
    Optional<BlogEntity> findByBlogId(String id);
    Optional<Long> countByUserId(Long userId);
    Optional<Long> deleteAllByUserEmail(String email);
    Optional<BlogEntity> findByBlogIdAndUserEmail(String id, String userEmail);
    List<BlogEntity> findTop3ByUserEmailOrderByCreatedAtDesc(String userEmail);
    List<BlogEntity> findTop3ByUserPublicIdOrderByCreatedAtDesc(UUID id);
    @Query("""
            SELECT new com.ThoughtNest.BlogService.dto.ShortBlogResponseDto(
                 Be.BlogId,
                 Be.blogTitle,
                 Be.blogContent,
                 Be.createAt,
                 null,
                 null,
                 null,
            ) FROM BlogEntity Be
            WHERE Be.userPublicId = :userPublicId
            ORDER BY Be.createAt DESC
            """)
    List<ShortBlogResponseDto> find3BlogByuserPublicId(@Param("userPublicId") UUID userPublicId, Pageable pageable);
}
