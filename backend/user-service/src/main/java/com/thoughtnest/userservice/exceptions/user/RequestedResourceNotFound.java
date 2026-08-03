package com.ThoughtNest.UserService.exceptions.user;

public class RequestedResourceNotFound extends RuntimeException {
    public RequestedResourceNotFound(String message) {
        super(message);
    }
}
