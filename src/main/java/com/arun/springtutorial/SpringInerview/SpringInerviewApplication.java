package com.arun.springtutorial.SpringInerview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
//@PropertySource("classpath:database.properties")
@PropertySource({"classpath:database.properties","classpath:security.properties"})
public class SpringInerviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringInerviewApplication.class, args);
	}
}
