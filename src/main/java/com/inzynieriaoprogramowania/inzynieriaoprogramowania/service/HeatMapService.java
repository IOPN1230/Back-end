package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.GeoJsonToArrayConverter;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.Point;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.Polygon;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.Shape;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

@Service
public class HeatMapService {

    ResourceLoader resourceLoader;
    GeoJsonToArrayConverter geoJsonToArrayConverter;
    ArrayList<ArrayList<Place>> placesArray;
    ArrayList<ArrayList<Place>> modifiedPlacesArray;
    HeatMapSolutions heatMapSolutions;


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

    public AreaMap convertHeatMapSolutions() throws JsonParseException, IOException {


        Resource resource = resourceLoader.getResource("classpath:map.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //If exists unknown element in JSON file, we can skip it without exception
        JsonNode map = objectMapper.readTree(resource.getFile());
        ArrayList<Point> pointsArray = geoJsonToArrayConverter.getAllPoints(map);
        ArrayList<Polygon> polygonsArray = geoJsonToArrayConverter.getAllPolygons(map);
        
        //Dzielimy sobie mapke na kwadraty (obszary)
		placesArray =  geoJsonToArrayConverter.createAreaMap();
		
		//Stworzenie tablicy ze zmodyfikowanymi juz wartosciami emission itp
		modifiedPlacesArray = geoJsonToArrayConverter.modifyPlaces(placesArray, pointsArray, polygonsArray);
		//I rozumiem, ze mamy zwrocic AreaMap (czyli moze po prostu ArrayList<ArrayList<>> ? )
		Place area[][] = this.convertArrayListToArray(modifiedPlacesArray);

		int testCounter = 0;
		for(int i = 0; i < modifiedPlacesArray.size(); i++){
		    for(int j = 0; j < modifiedPlacesArray.get(0).size(); j++){
                if(0 != modifiedPlacesArray.get(i).get(j).emission){
                    testCounter++;
                }
            }
        }
		//System.out.println("liczba emitujących placów = "+testCounter);
		heatMapSolutions = new HeatMapSolutions(new double[area.length][area[0].length]);
		for(int i = 0; i < heatMapSolutions.heatArray.length; i++){
		    for(int j = 0; j < heatMapSolutions.heatArray[0].length; j++){
		        heatMapSolutions.heatArray[i][j]=0.167;
            }
        }

		AreaMap areaMap = new AreaMap(area, heatMapSolutions);
		//Test of converter start
        double[][] testArray = new double[500][500];
        Random rand = new Random();
        for(int i = 0; i < 500; i++){
            for(int j = 0; j < 500; j++) {
                //TODO: Replace rand.nextDouble() with values from kernel
                //TODO: Move below conversions to SolutionsToBitmapConverter
                testArray[i][j]= rand.nextDouble();
            }
        }

        //Final testing
        Stage[] stages = {new Stage(1000,200,1)};
        Configuration configuration = new Configuration(stages);
        HeatMapAnalyser heatMapAnalyser = new HeatMapAnalyser(configuration);

        HeatMapSolutions heatMapSolutionsTest = heatMapAnalyser.calculateHeatMap(areaMap);

        /*for(int i = 0; i < heatMapSolutionsTest.heatArray.length; i++){
            for(int j = 0; j < heatMapSolutionsTest.heatArray[0].length; j++){
                if(1 < heatMapSolutionsTest.heatArray[i][j]) System.out.println(heatMapSolutionsTest.heatArray[i][j]);
            }
        }*/
        SolutionsToBitmapConverter solutionsToBitmapConverter = new SolutionsToBitmapConverter(heatMapSolutionsTest);
        solutionsToBitmapConverter.createBitmap();
        //Test of converter end

        return areaMap;
    }
    //Konwersja z arrayList na tablice dwuwymiarowa, wykorzystywane to bedzie wg preferencji obliczen
    public Place[][] convertArrayListToArray(ArrayList<ArrayList<Place>> placesTwoDimensionalArray)
    {
    	Place areaMap[][] = new Place[placesTwoDimensionalArray.size()][placesTwoDimensionalArray.get(0).size()];
    	int i=0, j=0;
    	for(ArrayList<Place> placesArray : placesTwoDimensionalArray)
    	{
    		for(Place place : placesArray)
    		{
    			areaMap[i][j] = place;
    			j++;
    		}
    		j=0;
    		i++;
    	}
    	return areaMap;
    }
}
