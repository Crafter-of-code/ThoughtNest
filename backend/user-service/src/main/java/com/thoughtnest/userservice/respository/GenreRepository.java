package com.thoughtnest.userservice.respository;

import com.thoughtnest.userservice.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity,Long> {

}
