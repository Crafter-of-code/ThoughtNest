package com.ThoughtNest.UserService.exceptions;

import com.ThoughtNest.UserService.dto.ResponseDto;
import com.ThoughtNest.UserService.exceptions.auth.EmailAlreadyExistsException;
import com.ThoughtNest.UserService.exceptions.user.AlreadyFollowingException;
import com.ThoughtNest.UserService.exceptions.user.RequestedResourceNotFound;
import com.ThoughtNest.UserService.exceptions.user.UserNotFoundException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseDto> handleUserNotFound(UserNotFoundException ex) {

        ResponseDto response = new ResponseDto();
        response.setStatus(false);
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(RequestedResourceNotFound.class)
    public ResponseEntity<ResponseDto> handleRequestedResourceNotFound(RequestedResourceNotFound ex) {

        ResponseDto response = new ResponseDto();
        response.setStatus(false);
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(AlreadyFollowingException.class)
    public ResponseEntity<ResponseDto> handleAlreadyFollowing(AlreadyFollowingException ex) {

        ResponseDto<Void> response = new ResponseDto<Void>();
        response.setStatus(false);
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ResponseDto<Void>> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {

        ResponseDto<Void> response = new ResponseDto<Void>();
        response.setStatus(false);
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<Void>> handleIllegalArgument(IllegalArgumentException ex) {

        ResponseDto<Void> response = new ResponseDto<Void>();
        response.setStatus(false);
        response.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseDto<Void>> handleDatabaseException(DataAccessException ex) {

        log.error("Database exception occurred", ex);

        ResponseDto<Void> response = new ResponseDto<Void>();
        response.setStatus(false);
        response.setMessage("A database error occurred. Please try again later.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<Void>> handleGlobalException(Exception ex) {

        log.error("Unexpected exception occurred", ex);

        ResponseDto<Void> response = new ResponseDto<Void>();
        response.setStatus(false);
        response.setMessage("An unexpected error occurred. Please try again later.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseDto<Void>> NoSuchElementExceptionHandler(NoSuchElementException ex){
        ResponseDto<Void> responseDto = new ResponseDto<Void>();
        responseDto.setStatus(false);
        responseDto.setMessage(ex.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto<Void>> methodArgumentTypeMismatchHandler(MethodArgumentTypeMismatchException ex){
        ResponseDto<Void> responseDto = new ResponseDto<Void>();
        responseDto.setStatus(false);
        responseDto.setMessage("You are sending the wrong variable type");
        System.out.println(ex.getMessage());
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDto);
    }
}
