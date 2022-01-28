package com.picture.ServiceImpl;

import java.util.Collection;
import java.util.Optional;

import com.picture.Dao.PictureDao;
import com.picture.Entity.Picture;
import com.picture.Service.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    PictureDao pictureDao;
    @Autowired
   public PictureServiceImpl(PictureDao pictureDao){
        this.pictureDao=pictureDao;
    }
    @Override
    public Picture save(Picture picture) {
       return this.pictureDao.save(picture);
        
    }

    @Override
    public Collection<Picture> allPictures() {
        return this.pictureDao.findAll();
    }
    @Override
    public Picture getById(Long id) {
       
        return this.pictureDao.getById(id);
    }
    @Override
    public void deleteById(Long id) {
     this.pictureDao.deleteById(id);
    }
    
}
