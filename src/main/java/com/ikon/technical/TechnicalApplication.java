package com.ikon.technical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ikon")
@EntityScan(basePackages = "com.ikon")
public class TechnicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalApplication.class, args);
	}

}
