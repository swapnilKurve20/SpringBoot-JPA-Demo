package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.demo")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// @GetMapping("/hello")
	// public String hello(@RequestParam(value = "name", defaultValue = "World")
	// String name) {
	// return String.format("Hello %s!", name);
	// }
}
