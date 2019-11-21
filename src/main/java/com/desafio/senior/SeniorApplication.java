package com.desafio.senior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.desafio.senior.model" })
@EnableJpaRepositories(basePackages = { "com.desafio.senior.repository" })
@ComponentScan(basePackages = { "com.desafio.senior.controller, " + " com.desafio.senior.model, "
		+ " com.desafio.senior.repository, " + " com.desafio.senior.service, " + " com.desafio.senior.util" })
public class SeniorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeniorApplication.class, args);
	}

}
