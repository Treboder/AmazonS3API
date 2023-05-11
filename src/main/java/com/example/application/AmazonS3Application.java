package com.example.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.example")
@SpringBootApplication(scanBasePackages = "com.example")
public class AmazonS3Application {

	public static void main(String[] args) {
		SpringApplication.run(AmazonS3Application.class, args);
	}

}
