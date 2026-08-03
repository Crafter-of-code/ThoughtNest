package com.ThoughtNest.UserService.service;

import com.ThoughtNest.UserService.dto.LoginRequestDto;
import com.ThoughtNest.UserService.dto.LogoutResponseDto;
import com.ThoughtNest.UserService.dto.auth.SigninResponseDto;
import com.ThoughtNest.UserService.dto.auth.SignupRequestDto;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.entity.UserFollow;
import com.ThoughtNest.UserService.entity.UserProfile;
import com.ThoughtNest.UserService.exceptions.auth.EmailAlreadyExistsException;
import com.ThoughtNest.UserService.repository.UserFollowRepository;
import com.ThoughtNest.UserService.repository.UserProfileRepo;
import com.ThoughtNest.UserService.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    private UserProfileRepo profileRepository;
    private UserFollowRepository userFollowRepository;
    @Transactional
    public SigninResponseDto signinService(SignupRequestDto request) {

        if (userRepository.existsByUserEmail(request.getUserEmail())) {
            throw new EmailAlreadyExistsException("The email address is already registered.");
        }

        UserEntity user = new UserEntity();
        user.setUserName(request.getUserName());
        user.setUserEmail(request.getUserEmail());
        user.setUserPassword(request.getUserPassword());

        UserEntity savedUser = userRepository.save(user);
        UserProfile profile = new UserProfile();
        profile.setUserEntity(savedUser);
        savedUser.setUserProfile(profile);
        profileRepository.save(profile);
//        UserFollow follow = new UserFollow();
//        follow.setFollower(savedUser);
//        follow.setFollowing(savedUser);
//        userFollowRepository.save(follow);
//        savedUser.getFollowers().add(follow);
//        savedUser.getFollowing().add(follow);
        SigninResponseDto response = new SigninResponseDto();
        response.setStatus(true);
        response.setMessage("Account created successfully.");

        return response;
    }
    public LoginRequestDto loginService(LoginRequestDto userData){
        LoginRequestDto userCredentailDetail = new LoginRequestDto();
        Optional<UserEntity> userDetail = userRepository
                .findByUserEmail(userData.getUserEmail());
        if(userDetail.isPresent()){
            userCredentailDetail.setUserEmail(userDetail.get().getUserEmail());
            userCredentailDetail.setUserPassword(userDetail.get().getUserPassword());
            userCredentailDetail.setPublicId(userDetail.get().getPublicId());
            System.out.println(userCredentailDetail.getPublicId());
        }
        return  userCredentailDetail;
    }
    public LogoutResponseDto logoutService(){
        LogoutResponseDto logoutResponseDto = new LogoutResponseDto();
        logoutResponseDto.setStatus(true);
        logoutResponseDto.setMessage("Logged out successfully");
        return  logoutResponseDto;
    }
}
