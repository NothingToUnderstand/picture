package com.picture.Dao;

import com.picture.Entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureDao extends JpaRepository<Picture,Long> {
    
}
