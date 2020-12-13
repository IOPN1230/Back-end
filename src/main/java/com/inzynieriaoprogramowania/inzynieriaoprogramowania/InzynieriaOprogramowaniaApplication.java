package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.GeoJsonToArrayConverter;

@SpringBootApplication
public class InzynieriaOprogramowaniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InzynieriaOprogramowaniaApplication.class, args);
		GeoJsonToArrayConverter converter = new GeoJsonToArrayConverter();
		converter.createAreaMap();
	}

}
