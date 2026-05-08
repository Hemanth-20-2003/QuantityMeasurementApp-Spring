package com.app.quantitymeasurement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application class for Quantity Measurement Application.
 * This is the entry point for the Spring Boot application that handles
 * quantity conversions and measurements (LENGTH, MASS, TEMPERATURE).
 */
@SpringBootApplication
public class QuantityMeasurementAppApplication {

	/**
	 * Main method to start the Spring Boot application.
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(QuantityMeasurementAppApplication.class, args);
	}

}
