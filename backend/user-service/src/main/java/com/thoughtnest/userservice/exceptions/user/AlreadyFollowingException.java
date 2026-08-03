package com.ThoughtNest.UserService.exceptions.user;

public class AlreadyFollowingException extends RuntimeException {
    public AlreadyFollowingException(String message) {
        super(message);
    }
}
