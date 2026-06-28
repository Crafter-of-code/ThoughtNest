package com.thoughtnest.userservice.requestDto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@Data
public class GenreListDto {
    private List<String> genreDataList;
}
