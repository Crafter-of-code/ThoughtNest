package com.ThoughtNest.UserService.service;

import com.ThoughtNest.UserService.Utility.JwtUtil;
import com.ThoughtNest.UserService.dto.ResponseDto;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.entity.UserFollow;
import com.ThoughtNest.UserService.exceptions.user.AlreadyFollowingException;
import com.ThoughtNest.UserService.exceptions.user.IllegalArgumentException;
import com.ThoughtNest.UserService.exceptions.user.RequestedResourceNotFound;
import com.ThoughtNest.UserService.exceptions.user.UserNotFoundException;
import com.ThoughtNest.UserService.repository.UserFollowRepository;
import com.ThoughtNest.UserService.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserFollowerService {
    private final UserFollowRepository userFollowRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Transactional
    public ResponseDto setFollower(String token, UUID publicId) {
        UserEntity follower = userRepository.findByUserEmail(jwtUtil.getClaims(token.substring(7)).getSubject())
                .orElseThrow(() -> new UserNotFoundException("Authenticated user not found."));
        UserEntity following = userRepository.findByPublicId(publicId)
                .orElseThrow(() -> new UserNotFoundException("User to follow not found."));
        if (follower.getPublicId().equals(following.getPublicId())) {
            throw new IllegalArgumentException("You cannot follow yourself.");
        }
        if (userFollowRepository.existsByFollowerAndFollowing(follower, following)) {
            throw new AlreadyFollowingException("You are already following this user.");
        }
        UserFollow follow = new UserFollow();
        follow.setFollower(follower);
        follow.setFollowing(following);
        ResponseDto response = new ResponseDto();
        try{
            userFollowRepository.save(follow);
            response.setStatus(true);
            response.setMessage("User followed successfully.");
            System.out.println("successfully followed");
        }catch (Exception e){
            System.out.println(e.getMessage());
            response.setStatus(false);
            response.setMessage("User followed successfully.");
        }
        System.out.println("successfully followed");
        return response;
    }
    @Transactional
    public ResponseDto removeFollowerService(String token, UUID userId){
      UserEntity follower =  userRepository.findByUserEmail(jwtUtil.getClaims(token.substring(7)).getSubject())
              .orElseThrow(()->new UserNotFoundException("You are not a valid user"));
      UserEntity following =  userRepository.findByPublicId(userId)
                .orElseThrow(()->new UserNotFoundException("User not found"));
      UserFollow follow = userFollowRepository.findByFollowerAndFollowing(follower,following)
              .orElseThrow(()->new RequestedResourceNotFound("Unable to find the relationship"));
        userFollowRepository.delete(follow);
        ResponseDto response = new ResponseDto();
        response.setStatus(true);
        response.setMessage("User unfollowed successfully.");
        System.out.println("successfully Unfollowed");
        return response;
    }
}
