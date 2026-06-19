package com.thoughtnest.userservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ResponseDto {
    private boolean status;
    private String message;
}
