package com.edoe.edoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EdoeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdoeApplication.class, args);
	}

}
