package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.dto.UserDto;
import com.ThoughtNest.BlogService.entity.BlogEntity;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.HashSet;
@Component
@RequiredArgsConstructor
public class BlogIntractionService {
    private final BlogRepository blogRepository;
    private static final int viewScore = 1;
    private static final int likeScore = 5;
    public boolean recordView(UserDto user, BlogEntity blogData){
        List<UUID> previousViewedData = blogData.getBlogViews();
        if(previousViewedData.contains(user.getPublicId())) return  true;
        previousViewedData.add(user.getPublicId());
        long getPreviousBlogInteractionScore = blogData.getBlogInteractionScore();
        blogData.setBlogInteractionScore(getPreviousBlogInteractionScore+viewScore);
        blogData.setBlogViews(previousViewedData);
        blogRepository.save(blogData);
        return  true;
    }
    public boolean recordLike(UserDto user, BlogEntity blogData){
        long previousInteractionScore = blogData.getBlogInteractionScore();
        List<UUID> blogLikesUUID = blogData.getBlogLikes();
        blogLikesUUID.add(user.getPublicId());
        blogData.setBlogLikes(blogLikesUUID);
        blogData.setBlogInteractionScore(previousInteractionScore +likeScore);
        blogRepository.save(blogData);
        return  true;
    }
    public boolean recordUnlike(UserDto userDto,BlogEntity blogData){
        long previousInteractionScore = blogData.getBlogInteractionScore();
        List<UUID> userLikeList = blogData.getBlogLikes();
        blogData.setBlogInteractionScore(previousInteractionScore-likeScore);
        userLikeList.remove(userDto.getPublicId());
        blogData.setBlogLikes(userLikeList);
        blogRepository.save(blogData);
        return  true;
    }
}
