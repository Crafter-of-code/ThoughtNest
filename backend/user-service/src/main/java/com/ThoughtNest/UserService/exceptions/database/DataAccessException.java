package com.ThoughtNest.UserService.exceptions.database;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }
}
