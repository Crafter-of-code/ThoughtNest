package com.ThoughtNest.BlogService.exceptions;

import com.ThoughtNest.BlogService.dto.ResponseDto;
import com.ThoughtNest.BlogService.exceptions.blog.BlogNotFoundException;
import com.ThoughtNest.BlogService.exceptions.general.IllegalArgumentException;
import com.ThoughtNest.BlogService.exceptions.user.UserNotFoundException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto> handleUserNotFound(UserNotFoundException ex){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(false);
        responseDto.setMessage(ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<ResponseDto> handlerBlogNotFound(BlogNotFoundException ex){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(false);
        responseDto.setMessage(ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto> handleIllegalArugment(IllegalArgumentException ex){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(false);
        responseDto.setMessage(ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ResponseDto<Void>> handleIllegalArugment(FeignException.NotFound ex){
        ResponseDto<Void> responseDto = new ResponseDto<Void>();
        responseDto.setStatus(false);
        responseDto.setMessage("We are unable to full fill you request right now");
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }
}
