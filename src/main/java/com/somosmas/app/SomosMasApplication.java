package com.somosmas.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SomosMasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SomosMasApplication.class, args);
	}

}
