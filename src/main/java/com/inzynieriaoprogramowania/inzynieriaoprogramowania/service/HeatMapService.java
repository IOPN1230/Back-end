package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.MapElement;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations.HeatMapSolutions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class HeatMapService {

    @Autowired
    ResourceLoader resourceLoader;

    public HeatMapService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String getHeatMap(int id) {
        try {
            convertHeatMapSolutions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "...przeliczam dla obiektu: " + id + "...zwracam bitmapę";
    }

    public HeatMapSolutions convertHeatMapSolutions() throws JsonParseException, IOException {


        Resource resource = resourceLoader.getResource("classpath:map.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //If exists unknown element in JSON file, we can skip it without exception
        MapElement mapElement = objectMapper.readValue(resource.getFile(), MapElement.class);
        System.out.println(mapElement.getType()); //It's working but the rest isn't
        /*for(int i = 0; i < mapElement.getFeaturesType().length; i++){
            System.out.println(mapElement.getFeaturesType()[i]);
        }
        for(int i = 0; i < mapElement.getGeometryType().length; i++){
            System.out.println(mapElement.getGeometryType()[i]);
        }*/
        return null;
    }
}
