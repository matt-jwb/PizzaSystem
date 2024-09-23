package com.example.assignment2springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Assignment2SpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assignment2SpringBootApplication.class, args);
	}

}
