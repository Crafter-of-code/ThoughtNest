package com.ThoughtNest.UserService.service;

import com.ThoughtNest.UserService.Utility.JwtUtil;
import com.ThoughtNest.UserService.clients.BlogFiegnClient;
import com.ThoughtNest.UserService.dto.*;
import com.ThoughtNest.UserService.dto.*;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.entity.UserProfile;
import com.ThoughtNest.UserService.dto.*;
import com.ThoughtNest.UserService.Utility.*;
import com.ThoughtNest.UserService.clients.*;
import com.ThoughtNest.UserService.entity.*;
import com.ThoughtNest.UserService.repository.UserRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private BlogFiegnClient blogFiegnClient;
    private JwtUtil jwtUtil;
    private Cloudinary cloudinary;
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
                ShortUserDataDto shortUserDataDto = new ShortUserDataDto(null,null,null);
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
        ResponseDto responseDto = new ResponseDto();
        Optional<UserEntity> userEntity = userRepository.findByPublicId(id);
        if(userEntity.isPresent()){
            CompleteUserDetailDto completeUserDetailDto = new CompleteUserDetailDto();
            UserEntity userDetail = userEntity.get();
            completeUserDetailDto.setPublicId(userDetail.getPublicId());
            completeUserDetailDto.setUserName(userDetail.getUserName());
            completeUserDetailDto.setNoOfFollowing((long)userDetail.getFollowing().size());
            completeUserDetailDto.setNoOfFollower((long)userDetail.getFollowers().size());
            Long noOfUserblog = blogFiegnClient.CountByUserId(token,userDetail.getUserId());
            completeUserDetailDto.setNoOfBlog(noOfUserblog);
            completeUserDetailDto.setFollowing(userEntity.get().getFollowers().stream().anyMatch(follwer->
                    follwer.getFollower().getUserEmail().equals(jwtUtil.getClaims(token.substring(7)).getSubject())));
            if(userDetail.getUserProfile() !=null) {
                CompleteUserDetailDto.UserProfileDto userProfileDto = new CompleteUserDetailDto.UserProfileDto();
                userProfileDto.setUserBio(userDetail.getUserProfile().getUserBio());
                userProfileDto.setUserLocation(userDetail.getUserProfile().getUserLocation());
                userProfileDto.setUserImageUrl(userDetail.getUserProfile().getUserImageUrl());
                userProfileDto.setUserTotalLike(userDetail.getUserProfile().getUserTotalLikes());
                userProfileDto.setUserProfileView((long)userDetail.getUserProfile().getUserProfileView().size());
                userProfileDto.setUserPublished(noOfUserblog);
                userProfileDto.setUserTopics(userDetail.getUserProfile().getUserTopic());
                userProfileDto.setUserJoinedOn(userDetail.getCreatedAt());
                userProfileDto.setUserPublished(noOfUserblog);
                userProfileDto.setUserTopics(userDetail.getUserProfile().getUserTopic());
                System.out.println(userDetail.getUserProfile().getUserTopic());
                completeUserDetailDto.setUserProfile(userProfileDto);
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
    public ResponseDto<OwnerDataDto> getOwnDetailsService(String token) {

        ResponseDto<OwnerDataDto> responseDto = new ResponseDto<>();

        String userEmail = jwtUtil
                .getClaims(token.substring(7))
                .getSubject();

        Optional<UserEntity> userOwnerDetail =
                userRepository.findByUserEmail(userEmail);
        if (userOwnerDetail.isEmpty()) {
            responseDto.setStatus(false);
            responseDto.setMessage("We are unable to fetch the user detail");
            return responseDto;
        }

        UserEntity user = userOwnerDetail.get();
        long noOfUserBlog =
                blogFiegnClient.CountByUserId(token, user.getUserId());
        OwnerDataDto ownerDataDto = new OwnerDataDto();

        ownerDataDto.setUserId(user.getUserId());
        ownerDataDto.setPublicId(user.getPublicId());
        ownerDataDto.setUserName(user.getUserName());
        ownerDataDto.setUserEmail(user.getUserEmail());

        ownerDataDto.setNoOfFollower(
                user.getFollowers() != null
                        ? (long) user.getFollowers().size()
                        : 0L
        );

        ownerDataDto.setNoOfFollowing(
                user.getFollowing() != null
                        ? (long) user.getFollowing().size()
                        : 0L
        );

        ownerDataDto.setCreatedAt(user.getCreatedAt());
        System.out.println(user.getFollowing());
        OwnerDataDto.UserProfileDto userProfileDto =
                new OwnerDataDto.UserProfileDto();

        if (user.getUserProfile() != null) {

            userProfileDto.setUserLocation(
                    user.getUserProfile().getUserLocation()
            );

            userProfileDto.setUserBio(
                    user.getUserProfile().getUserBio()
            );

            userProfileDto.setUserPublished(
                    noOfUserBlog
            );

            userProfileDto.setUserProfileView(
                    user.getUserProfile().getUserProfileView() != null
                            ? (long) user.getUserProfile()
                            .getUserProfileView()
                            .size()
                            : 0L
            );

            userProfileDto.setUserTotalLikes(
                    user.getUserProfile().getUserTotalLikes()
            );

            userProfileDto.setUserImageUrl(
                    user.getUserProfile().getUserImageUrl()
            );

            userProfileDto.setUserTopics(
                    user.getUserProfile().getUserTopic()
            );
        }

        ownerDataDto.setUserProfile(userProfileDto);
        OwnerDataDto.UserFollow userFollow =
                new OwnerDataDto.UserFollow();
        if (user.getFollowers() != null &&
                !user.getFollowers().isEmpty()) {

            List<ShortUserDataDto> followers =
                    user.getFollowers()
                            .stream()
                            .map(item -> {

                                String userImageUrl = null;

                                if (item.getFollower() != null &&
                                        item.getFollower().getUserProfile() != null) {

                                    userImageUrl = item.getFollower()
                                            .getUserProfile()
                                            .getUserImageUrl();
                                }

                                return new ShortUserDataDto(
                                        item.getFollower().getUserName(),
                                        item.getFollower().getPublicId(),
                                        userImageUrl
                                );
                            })
                            .toList();

            userFollow.setUserFollower(followers);
        }
        if (user.getFollowing() != null &&
                !user.getFollowing().isEmpty()) {

            List<ShortUserDataDto> following =
                    user.getFollowing()
                            .stream()
                            .map(item -> {

                                String userImageUrl = null;

                                if (item.getFollowing() != null &&
                                        item.getFollowing().getUserProfile() != null) {

                                    userImageUrl = item.getFollowing()
                                            .getUserProfile()
                                            .getUserImageUrl();
                                }

                                return new ShortUserDataDto(
                                        item.getFollowing().getUserName(),

                                        // IMPORTANT:
                                        // Following user's publicId
                                        item.getFollowing().getPublicId(),

                                        userImageUrl
                                );
                            })
                            .toList();

            userFollow.setUserFollowing(following);
        }

        ownerDataDto.setUserFollow(userFollow);
        responseDto.setStatus(true);
        responseDto.setMessage("We found your detail");
        responseDto.setData(ownerDataDto);

        return responseDto;
    }
    public ResponseDto updateOwnerDetail(String token, PatchUserDetailRequestDto updatedUserDetail) throws IOException {
        ResponseDto responseDto = new ResponseDto();
        Optional<UserEntity> userEntity = userRepository.findByUserEmail(jwtUtil.getClaims(token).getSubject());
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            // Create profile if it doesn't exist
            if (user.getUserProfile() == null) {
                UserProfile profile = new UserProfile();
                // Set BOTH sides of the relationship
                profile.setUserEntity(user);
                user.setUserProfile(profile);
                profile.setUserTopic(new HashSet<>());
            }
            UserProfile profile = user.getUserProfile();
            if (updatedUserDetail.getUserName() != null
                    && !Objects.equals(user.getUserName(), updatedUserDetail.getUserName())) {

                user.setUserName(updatedUserDetail.getUserName());
            }
            if (updatedUserDetail.getUserLocation() != null
                    && !Objects.equals(profile.getUserLocation(), updatedUserDetail.getUserLocation())) {

                profile.setUserLocation(updatedUserDetail.getUserLocation());
            }

            if (updatedUserDetail.getUserBio() != null
                    && !Objects.equals(profile.getUserBio(), updatedUserDetail.getUserBio())) {

                profile.setUserBio(updatedUserDetail.getUserBio());
            }

            // Upload profile image
            if (updatedUserDetail.getUserProfileData() != null
                    && !updatedUserDetail.getUserProfileData().isEmpty()) {

                Map uploadedImageData = cloudinary
                        .uploader()
                        .upload(
                                updatedUserDetail.getUserProfileData().getBytes(),
                                ObjectUtils.emptyMap());

                profile.setUserImageUrl(uploadedImageData.get("secure_url").toString());
                profile.setUserImagePublicUrl(uploadedImageData.get("public_id").toString());
            }

            // Update topics
            if (updatedUserDetail.getUserTopic() != null
                    && !Objects.equals(profile.getUserTopic(), updatedUserDetail.getUserTopic())) {

                profile.setUserTopic(new HashSet<>(updatedUserDetail.getUserTopic()));
            }

            userRepository.save(user);

            responseDto.setStatus(true);
            responseDto.setMessage("Data updated successfully");

        } else {

            responseDto.setStatus(false);
            responseDto.setMessage("Can't find your data");
            responseDto.setData(null);
        }
        return  responseDto;
    }
    @Transactional
    public ResponseDto deleteOwnerAccount(String token){
        ResponseDto responseDto = new ResponseDto();
        String email = jwtUtil.getClaims(token.substring(7)).getSubject();

        UserEntity user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean blogDeleted = blogFiegnClient.deleteOwnerBlog(token);

        if (!blogDeleted) {
            responseDto.setStatus(false);
            responseDto.setMessage("unable to delete user");
            return responseDto;
        }

        userRepository.delete(user);
        responseDto.setStatus(true);
        responseDto.setMessage("user has been successfully deleted");
        return responseDto;
    }
}
