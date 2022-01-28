package com.picture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

public class PictureApplication {

	public static void main(String[] args) {
		SpringApplication.run(PictureApplication.class, args);
	}

}
