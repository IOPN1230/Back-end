package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.inzynieriaoprogramowania.inzynieriaoprogramowania.GeoJsonToArrayConverter;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.controller.HeatMapController;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.HeatMapService;

@SpringBootApplication
public class InzynieriaOprogramowaniaApplication {

	public static void main(String[] args) throws JsonParseException, Exception {
	
		SpringApplication.run(InzynieriaOprogramowaniaApplication.class, args);
	}

}
