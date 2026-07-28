package com.ThoughtNest.UserService.service;

import com.ThoughtNest.UserService.Utility.JwtUtil;
import com.ThoughtNest.UserService.clients.BlogFiegnClient;
import com.ThoughtNest.UserService.dto.*;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.repository.UserRepository;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private BlogFiegnClient blogFiegnClient;
    private JwtUtil jwtUtil;
    /*
    search by user name
     */
    public ResponseDto<ShortUserDataDto> getUserByUserNameController(String userName){
        Optional<List<UserEntity>> users = userRepository.findByUserNameContainingIgnoreCase(userName);
        ResponseDto response = new ResponseDto();
        if(users.isPresent()){
            List<ShortUserDataDto> listShortUserDataDto = new ArrayList<ShortUserDataDto>();
            response.setStatus(true);
            response.setMessage("user found");
            users.get().stream().forEach(items->{
                ShortUserDataDto shortUserDataDto = new ShortUserDataDto();
                shortUserDataDto.setPublicId(items.getPublicId());
                shortUserDataDto.setUserName(items.getUserName());
                if(items.getUserProfile() != null){
                    shortUserDataDto.setUserProfileImage(items.getUserProfile().getUserImageUrl());
                }
                listShortUserDataDto.add(shortUserDataDto);
            });
            response.setData(listShortUserDataDto);
        }else{
            response.setStatus(false);
            response.setMessage("user not found");
        }
        return  response;
    }
    /*
    get by user id
     */
    public ResponseDto getUserDetailService(String token ,UUID id){
        System.out.println(id);
        ResponseDto responseDto = new ResponseDto();
        Optional<UserEntity> userEntity = userRepository.findByPublicId(id);
        System.out.println(userEntity);
        if(userEntity.isPresent()){
            CompleteUserDetailDto completeUserDetailDto = new CompleteUserDetailDto();
            UserEntity userDetail = userEntity.get();
            completeUserDetailDto.setPublicId(userDetail.getPublicId());
            completeUserDetailDto.setUserName(userDetail.getUserName());
            completeUserDetailDto.setNoOfFollowing((long)userDetail.getFollowing().size());
            completeUserDetailDto.setNoOfFollower((long)userDetail.getFollowers().size());
            Long noOfUserblog = blogFiegnClient.CountByUserId(token,userDetail.getUserId());
            completeUserDetailDto.setNoOfBlog(noOfUserblog);
            CompleteUserDetailDto.UserProfileDto userProfileDto = new CompleteUserDetailDto.UserProfileDto();
            if(userDetail.getUserProfile() !=null) {
                userProfileDto.setUserBio(userDetail.getUserProfile().getUserBio());
                userProfileDto.setUserLocation(userDetail.getUserProfile().getUserLocation());
                userProfileDto.setUserImageUrl(userDetail.getUserProfile().getUserImageUrl());
                userProfileDto.setUserTotalLike(userDetail.getUserProfile().getUserTotalLikes());
                userProfileDto.setUserProfileView(userDetail.getUserProfile().getUserProfileView());
                userProfileDto.setUserPublished(userDetail.getUserProfile().getUserPublished());
            }
            responseDto.setStatus(true);
            responseDto.setMessage("we gather info successfully");
            responseDto.setData(completeUserDetailDto);
        }else{
            responseDto.setStatus(false);
            responseDto.setMessage("Unable to find the user data");
        }
        return responseDto;
    }
    public ResponseDto getOwnDetailsService(String token){
        String userEmail = jwtUtil.getClaims(token).getSubject();
        ResponseDto responseDto = new ResponseDto();
        OwnerDataDto ownerDataDto = new OwnerDataDto();
        OwnerDataDto.userProfileDto userProfileDto = new OwnerDataDto.userProfileDto();
        Optional<UserEntity> userOwnerDetail = userRepository.findByUserEmail(userEmail);
        if(userOwnerDetail.isPresent()){
            responseDto.setStatus(true);
            responseDto.setMessage("We found your detail");
            ownerDataDto.setUserId(userOwnerDetail.get().getUserId());
            ownerDataDto.setPublicId(userOwnerDetail.get().getPublicId());
            ownerDataDto.setUserName(userOwnerDetail.get().getUserName());
            ownerDataDto.setUserEmail(userOwnerDetail.get().getUserEmail());
            ownerDataDto.setNoOfFollower((long)userOwnerDetail.get().getFollowers().size());
            ownerDataDto.setNoOfFollowing((long)userOwnerDetail.get().getFollowing().size());
            ownerDataDto.setCreatedAt(userOwnerDetail.get().getCreatedAt());
            if(userOwnerDetail.get().getUserProfile() != null){
                userProfileDto.setUserLocation(userOwnerDetail.get().getUserProfile().getUserLocation());
                userProfileDto.setUserBio(userOwnerDetail.get().getUserProfile().getUserBio());
                userProfileDto.setUserPublished(userOwnerDetail.get().getUserProfile().getUserPublished());
                userProfileDto.setUserProfileView(userOwnerDetail.get().getUserProfile().getUserProfileView());
                userProfileDto.setUserTotalLikes(userOwnerDetail.get().getUserProfile().getUserTotalLikes());
                userProfileDto.setUserImageUrl(userOwnerDetail.get().getUserProfile().getUserImageUrl());
            }
            responseDto.setData(ownerDataDto);
        }else{
            responseDto.setStatus(false);
            responseDto.setMessage("We are unable to fetch the user detail");
        }
        return  responseDto;
    }
}
