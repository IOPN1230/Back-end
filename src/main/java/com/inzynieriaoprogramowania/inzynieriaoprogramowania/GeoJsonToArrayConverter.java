package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class GeoJsonToArrayConverter {
    //TODO: Add method which creates AreaMap
    //TODO: Add method which modifies Places
    //TODO: Add custom exception in case of wrong json file
    public ArrayList<Shape> getAllFigures(JsonNode map) {
        ArrayList<Shape> polygons = new ArrayList<>();
        //System.out.println("siusiak1");
        JsonNode features = map.path("features");
        //System.out.println("siusiak2");
        Iterator<JsonNode> featuresIterator = features.iterator();
        //System.out.println("siusiak3");
        while(featuresIterator.hasNext()){
            //System.out.println("siusiak4");
            JsonNode figure = featuresIterator.next();
            //System.out.println("siusiak5");
            JsonNode coordinates = figure.path("geometry").path("coordinates");
            //System.out.println("siusiak6");
            Iterator<JsonNode> coordinatesIterator = coordinates.iterator();
            Shape shape = new Shape();
            boolean isX = true;
            while(coordinatesIterator.hasNext()){
                //System.out.println("siusiak7");
                JsonNode point = coordinatesIterator.next();
                if(point.getNodeType().toString().equals("NUMBER")){
                    if (isX){
                        shape.getCoordinatesX().add(point.asDouble());
                    }
                    else{
                        shape.getCoordinatesY().add(point.asDouble());
                    }
                    isX=!isX;
                }
                else{
                    //System.out.println(point.getNodeType().toString());
                    Iterator<JsonNode> it = point.elements();
                    Double coordinateX = it.next().asDouble();
                    Double coordinateY = it.next().asDouble();
                    //System.out.println(coordinateX);
                    //System.out.println(coordinateY);
                    shape.getCoordinatesX().add(coordinateX);
                    shape.getCoordinatesY().add(coordinateY);
                }
            }
            polygons.add(shape);
        }
        return polygons;
    }
}
