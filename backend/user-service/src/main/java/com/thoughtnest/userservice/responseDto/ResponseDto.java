package com.thoughtnest.userservice.responseDto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Data
@Getter
@Setter
public class ResponseDto {
    private boolean status;
    private String message;
    private String token;
}
