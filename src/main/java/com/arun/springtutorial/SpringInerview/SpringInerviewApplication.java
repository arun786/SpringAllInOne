package com.arun.springtutorial.SpringInerview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:database.properties")
public class SpringInerviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringInerviewApplication.class, args);
	}
}
