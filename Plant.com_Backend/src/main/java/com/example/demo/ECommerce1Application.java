package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })@ComponentScan({ "com.server", "com.server.config" })
public class ECommerce1Application {

	public static void main(String[] args) {
		SpringApplication.run(ECommerce1Application.class, args);
	}

}
