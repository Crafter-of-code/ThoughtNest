package com.ThoughtNest.BlogService.service;

import com.ThoughtNest.BlogService.repository.BlogRepository;
import com.ThoughtNest.BlogService.utility.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Provider;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AskBlogService {
    private BlogRepository blogRepository;
    private JwtUtil jwtUtil;
    public Long blogCountService(Long userId){
        Optional<Long> countBy = blogRepository.countByUserId(userId);
        countBy.ifPresent(System.out::println);
       return countBy.orElse(0L);
    }
    public Boolean deleteUserAllBlogsController(String token){
        String userEmail  = jwtUtil.extractUsername(token);
        try{
            Optional<Long> recordDelete = blogRepository.deleteAllByUserEmail(userEmail);
            if(recordDelete.isPresent()){
                if(recordDelete.get()>=0){

                return  true;
                }else{
                    return  false;
                }
            }
        }
        catch (Exception e){
            return  false;
        }
        return  true;
    }
    @Transactional
    public boolean deleteUserAllBlog(String token){
        try{
            blogRepository.deleteAllByUserEmail(jwtUtil.extractUsername(token));
            return  true;
        }catch (Exception e){
            return  true;
        }

    }
}
