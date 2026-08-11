package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.client.UserFeignClient;
import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.dto.ShortBlogResponseDto;
import com.ThoughtNest.BlogService.dto.blog.FollowingTopThreeBlogDto;
import com.ThoughtNest.BlogService.dto.user.ShortUserDetailDto;
import com.ThoughtNest.BlogService.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowingBlogService {
    public final UserFeignClient userFeignClient;
    public final BlogRepository blogRepository;
    public ResponseDto<List<FollowingTopThreeBlogDto>> getFollowingBlogService(String token){
        System.out.println("user following blog service has been called");
        List<ShortUserDetailDto> shortUserDetailList = userFeignClient.getUserFollowings(token);
        List<FollowingTopThreeBlogDto> followingTopThreeBlogDtos = new ArrayList<>();
        System.out.println(shortUserDetailList);
        shortUserDetailList.forEach(user->{
            Pageable pageable = PageRequest.of(0,3);
            List<ShortBlogResponseDto> shortBlogResponseDtos = blogRepository
                    .find3BlogByuserPublicId(user.getPublicId(),pageable);
            followingTopThreeBlogDtos
                    .add(new FollowingTopThreeBlogDto(user.getUserName()
                            ,user.getPublicId()
                            ,user.getUserProfileImage()
                            ,shortBlogResponseDtos));
        });
        ResponseDto<List<FollowingTopThreeBlogDto>> responseDto = new ResponseDto<List<FollowingTopThreeBlogDto>>();
        responseDto.setStatus(true);
        responseDto.setMessage("we successfully generate response for your request");
        responseDto.setData(followingTopThreeBlogDtos);
        return  responseDto;
    }
}
