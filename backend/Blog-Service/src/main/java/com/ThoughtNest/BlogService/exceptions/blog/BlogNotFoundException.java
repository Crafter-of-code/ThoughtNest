package com.ThoughtNest.BlogService.exceptions.blog;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(String message) {
        super(message);
    }
}
