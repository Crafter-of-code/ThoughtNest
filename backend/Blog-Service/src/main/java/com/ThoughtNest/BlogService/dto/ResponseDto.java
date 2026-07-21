package com.ThoughtNest.BlogService.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto<T> {
    private boolean status;
    private String message;
    private T data;
}
