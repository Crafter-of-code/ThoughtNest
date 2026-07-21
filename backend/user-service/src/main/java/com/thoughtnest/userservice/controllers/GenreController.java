package com.ThoughtNest.UserService.controllers;

import com.ThoughtNest.UserService.dto.ResponseDto;
import com.ThoughtNest.UserService.entity.GenreEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class GenreController {
    @GetMapping("/Genres")
    public ResponseEntity<List<GenreEntity>> getAllGenreController(){
        return null;
    }
    @PostMapping("/Genres")
    public ResponseEntity<ResponseDto>   postAllGenreController(){
        return  null;
    }
}
