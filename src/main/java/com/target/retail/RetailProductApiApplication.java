package com.target.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RetailProductApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailProductApiApplication.class, args);
	}

}
