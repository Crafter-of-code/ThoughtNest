package com.thoughtnest.userservice.services;

import com.thoughtnest.userservice.entity.GenreEntity;
import com.thoughtnest.userservice.requestDto.GenreListDto;
import com.thoughtnest.userservice.responseDto.ResponseDto;
import com.thoughtnest.userservice.respository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    private GenreRepository genreRepository;
    private ResponseDto responseDto;
    public List<GenreEntity> getAllGenre(){
        return genreRepository.findAll();
    }
    public ResponseDto addGenre(GenreListDto genreData){
       try{
//           genreRepository.save(genreData);
           for(int i = 0;i<genreData.getGenreDataList().size();i++){
               GenreEntity genreEntity = new GenreEntity();
               String genreName = genreData.getGenreDataList().get(i);
               genreEntity.setGenreName(genreName);
               genreRepository.save(genreEntity);
           }
           responseDto.setStatus(true);
           responseDto.setMessage("Genre has been added");
           return responseDto;
       }catch (Exception e){
           responseDto.setStatus(false);
           responseDto.setMessage("Getting error while saving you Genre");
           return responseDto;
       }
    }
    public ResponseDto deleteGenre(Long genreId){
        try{
            genreRepository.deleteById(genreId);
            responseDto.setStatus(true);
            responseDto.setMessage("It has been deleted successfully");
            return responseDto;
        }catch(Exception e){
            responseDto.setStatus(false);
            responseDto.setMessage("Facing problem while delete the genre");
            return responseDto;
        }

    }
}
