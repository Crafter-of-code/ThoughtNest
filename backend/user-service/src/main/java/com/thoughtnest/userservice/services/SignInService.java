package com.thoughtnest.userservice.services;

import com.thoughtnest.userservice.entity.UserEntity;
import com.thoughtnest.userservice.responseDto.ResponseDto;
import com.thoughtnest.userservice.respository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignInService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ResponseDto responseDto;
    // SigninService
    public ResponseDto registerNewUser(UserEntity userData){
        if(userData.getUserEmail().contains("@")){
            try{
                String encodedPassword = passwordEncoder.encode(userData.getUserPassword());
                System.out.println("Encoded user password");
                userData.setUserPassword(encodedPassword);
                userRepository.save(userData);
                responseDto.setStatus(true);
                responseDto.setMessage("Your Credential has been registered successfully");
            }catch (DataIntegrityViolationException e) {
//                throw new RuntimeException("Email already exists");
                responseDto.setStatus(false);
                responseDto.setMessage("Email already exists");
            }
            catch (Exception e){
                System.out.println(e);
                responseDto.setStatus(false);
                responseDto.setMessage("Facing error while registering the user");
            }
        }
        else{
            responseDto.setStatus(false);
            responseDto.setMessage("You Email is not correct");
        }
        return  responseDto;
    }
}
