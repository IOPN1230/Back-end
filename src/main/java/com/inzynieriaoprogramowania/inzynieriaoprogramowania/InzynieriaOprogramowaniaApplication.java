package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.JsonParseException;

@SpringBootApplication
public class InzynieriaOprogramowaniaApplication {

	public static void main(String[] args) throws JsonParseException, Exception {
		SpringApplication.run(InzynieriaOprogramowaniaApplication.class, args);
		//HeatMapService heatMapService = new HeatMapService(new DefaultResourceLoader());
		//heatMapService.convertHeatMapSolutions();
	}

}
