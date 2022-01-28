package com.picture.Controller;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.picture.Entity.Picture;
import com.picture.Service.PictureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.spring5.util.FieldUtils;

@RestController
public class PictureController {

    
    private String uploadPath="picture/src/main/resources/img";
    private static final Logger log = LoggerFactory.getLogger(PictureController.class);
  
    private PictureService pictureService;
    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

//Save Img
@PostMapping(value="/save")
public ResponseEntity<?> saveImage(@RequestBody MultipartFile img)throws IOException{
    log.info("saveImage");
    log.info("IMG:{}",img);

    
    //save into DataBase
 Picture picture=new Picture();
 picture.setName(img.getOriginalFilename());
 picture.setСontentType(img.getContentType());
 picture.setImg(img.getBytes());
 pictureService.save(picture);



//save into server----@value upload.path
String uuidFile = UUID.randomUUID().toString();
String resultFilename = uuidFile + "." + img.getOriginalFilename();
File file=new File(uploadPath,resultFilename);
file.createNewFile();
try(FileOutputStream fos=new  FileOutputStream(file)){        
    fos.write(img.getBytes(), 0,img.getBytes().length);
}catch(IOException ex){
    log.info("Error:{}",ex.getMessage());
}
    return  ResponseEntity.ok(picture);
}
//Get All
@GetMapping(value="/all")
public ResponseEntity<?> getAllImages(){
    Collection<Picture> pic= this.pictureService.allPictures();
    return ResponseEntity.ok(pic);
}
//Get by ID
@GetMapping(value="/img/{id}")
public ResponseEntity<?> getPictureById(@PathVariable("id")Long id) throws IOException{
   Picture pic= this.pictureService.getById(id);
    return ResponseEntity.ok(pic.getImg());
    // .header("filename", pic.getName())
    // .contentType(MediaType.valueOf(pic.getСontentType()))
    // .body(new InputStreamResource(new ByteArrayInputStream(pic.getImg()))); 
}

//Delete by ID
@DeleteMapping(value="/del/{id}")
public ResponseEntity<?> delPictureById(@PathVariable("id")Long id) throws IOException{
    Picture pic= this.pictureService.getById(id);
    this.pictureService.deleteById(id);
     return  ResponseEntity.ok("Delete is successfull"+pic.getId());
}
}

    

