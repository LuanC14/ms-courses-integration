package com.mentoria.ms_curso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MsCursoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCursoApplication.class, args);
	}
}
