package com.picture.Service;

import java.util.Collection;
import java.util.Optional;

import com.picture.Entity.Picture;

import org.springframework.stereotype.Service;

@Service
public interface PictureService {
   Picture save(Picture picture);
    Collection<Picture> allPictures();
    Picture getById(Long id);
    void deleteById(Long id);
}
