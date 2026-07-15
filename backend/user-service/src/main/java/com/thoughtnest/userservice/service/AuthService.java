package com.ThoughtNest.UserService.service;

import com.ThoughtNest.UserService.dto.LoginRequestDto;
import com.ThoughtNest.UserService.dto.LoginResponseDto;
import com.ThoughtNest.UserService.dto.SigninRepsonseDto;
import com.ThoughtNest.UserService.entity.UserEntity;
import com.ThoughtNest.UserService.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private UserRepository userRepository;
    public SigninRepsonseDto signinService(UserEntity userData){
        SigninRepsonseDto signinRepsonseDto = new SigninRepsonseDto();
        try{
            UserEntity savedUserData = userRepository.save(userData);
            if(savedUserData.getUserEmail() != null){
                signinRepsonseDto.setStatus(true);
                signinRepsonseDto.setMessage("Account Created");
                return  signinRepsonseDto;
            }else{
                signinRepsonseDto.setStatus(false);
                signinRepsonseDto.setMessage("Facing problem while creating the account");
                return  signinRepsonseDto;
            }
        }catch (DataIntegrityViolationException e){
            System.out.println(e.getMostSpecificCause().getMessage());
            signinRepsonseDto.setStatus(false);
            signinRepsonseDto.setMessage("The email address has already been registered");
            return  signinRepsonseDto;
        }catch(Exception e){
            System.out.println(e.getMessage());
            signinRepsonseDto.setStatus(false);
            signinRepsonseDto.setMessage("Facing problem while creating the account");
            return  signinRepsonseDto;
        }
    }
    public LoginRequestDto loginService(LoginRequestDto userData){
        LoginRequestDto userCredentailDetail = new LoginRequestDto();
        Optional<UserEntity> userDetail = userRepository
                .findByUserEmail(userData.getUserEmail());
        if(userDetail.isPresent()){
            userCredentailDetail.setUserEmail(userDetail.get().getUserEmail());
            userCredentailDetail.setUserPassword(userDetail.get().getUserPassword());
        }
        return  userCredentailDetail;
    }
}
