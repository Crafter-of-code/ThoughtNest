package com.ThoughtNest.BlogService.repository;

import com.ThoughtNest.BlogService.dto.BlogDetailResponseDto;
import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends MongoRepository<BlogEntity,String> {
    Optional<List<BlogEntity>> findByUserId(Long userId);
    Optional<List<ShortBlogResponseDto>> findTop20ByOrderByCreatedAtDesc();
    Optional<BlogDetailResponseDto> findByBlogId(String id);
}
