package com.thoughtnest.userservice.controllers;

import com.thoughtnest.userservice.entity.GenreEntity;
import com.thoughtnest.userservice.requestDto.GenreListDto;
import com.thoughtnest.userservice.responseDto.ResponseDto;
import com.thoughtnest.userservice.services.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GenreController {
    private GenreService genreService;
    private ResponseDto responseDto;
    ///
    ///
    @GetMapping("/genres")
    public ResponseEntity<List<GenreEntity>> getAllGenre(){
        List<GenreEntity> genreEntities = genreService.getAllGenre();
        if(genreEntities.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(genreEntities);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(genreEntities);
        }
    }
    @PostMapping("/admin/genres")
    public ResponseEntity<ResponseDto> addGenre(@RequestBody GenreListDto data){
        System.out.println(data);
        responseDto = genreService.addGenre(data);
        if(responseDto.isStatus()){
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }
    @DeleteMapping("/admin/genres/{id}")
    public ResponseEntity<ResponseDto> deleteGenre(@PathVariable Long id){
        responseDto = genreService.deleteGenre(id);
        if(responseDto.isStatus()){
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
        }
    }
}
