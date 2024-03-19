package com.equalexperts.recipefinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RecipeFinderApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecipeFinderApplication.class, args);
	}
}