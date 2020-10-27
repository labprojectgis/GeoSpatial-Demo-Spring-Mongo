package com.freelance.geospatial.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.freelance.geospatial.demo.controller.GeoController;
import com.freelance.geospatial.demo.repository.GeoRepository;

@SpringBootApplication
public class GeospatialDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeospatialDemoApplication.class, args);
	}
}