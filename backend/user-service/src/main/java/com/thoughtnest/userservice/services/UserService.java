package com.thoughtnest.userservice.services;

import com.thoughtnest.userservice.requestDto.LoginRequestDto;
import com.thoughtnest.userservice.responseDto.ResponseDto;
import com.thoughtnest.userservice.entity.UserEntity;
import com.thoughtnest.userservice.respository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private ResponseDto responseDto;
    private UserRepository userRepository;
}
