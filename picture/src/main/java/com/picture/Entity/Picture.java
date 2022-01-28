package com.picture.Entity;

import javax.persistence.*;
import lombok.Data;


@Entity
@Table(name="picture")
@Data
public class Picture {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id")
   private Long id;
   @Column(name="name")
   private String name;
   @Column(name="img")
  
   private byte[] img;
   @Column(name="сontentType")
   private String сontentType;
    
}
