package com.naher_farhsa.ATMSpringboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtmSpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(AtmSpringbootApplication.class, args);
	}
	// AtmConsole -> HTTP request -> AtmController -> AtmService
}





