package com.TugasAkhir.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		System.out.println("XXX Initizalize App\nInitialize App\nInitialize App");
		SpringApplication.run(Application.class, args);
		System.out.println("Initizalize App Finished\nInitialize App Finished\nInitialize App Finished");
	}
}
