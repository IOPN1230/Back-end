package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.GeoJsonToArrayConverter;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.Point;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.Shape;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations.HeatMapSolutions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations.Place;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class HeatMapService {

    ResourceLoader resourceLoader;
    GeoJsonToArrayConverter geoJsonToArrayConverter;

    public HeatMapService(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.geoJsonToArrayConverter = new GeoJsonToArrayConverter();
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
        JsonNode map = objectMapper.readTree(resource.getFile());
        ArrayList<Point> pointsArray = geoJsonToArrayConverter.getAllPoints(map);
        /*for (Shape polygon : polygons) {
            System.out.println(polygon.toString());
        }*/
        
        GeoJsonToArrayConverter converter = new GeoJsonToArrayConverter();
        //Dzielimy sobie mapke na kwadraty (obszary)
		ArrayList<ArrayList<Place>> placesArray =  converter.createAreaMap();
		//Stworzenie tablicy ze zmodyfikowanymi juz wartosciami emission itp
		ArrayList<ArrayList<Place>> modifiedPlacesArray = converter.modifyPlaces(placesArray, pointsArray);
		//I rozumiem, ze mamy zwrocic HeatMapSolutions (czyli moze po prostu ArrayList<ArrayList<>> ? )
        return null;
    }
}
