package com.edoe.edoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EdoeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdoeApplication.class, args);
	}

}
