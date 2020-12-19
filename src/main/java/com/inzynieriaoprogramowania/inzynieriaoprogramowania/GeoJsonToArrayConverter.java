package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import com.fasterxml.jackson.databind.JsonNode;
import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations.Place;

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
                    shape.getCoordinatesX().add(coordinateX);
                    shape.getCoordinatesY().add(coordinateY);
                }
            }
            polygons.add(shape);
        }
        return polygons;
    }
    
    public ArrayList<Point> getAllPoints(JsonNode map)
    {
    	ArrayList<Point> points = new ArrayList<>();
        JsonNode features = map.path("features");
        Iterator<JsonNode> featuresIterator = features.iterator();
        while(featuresIterator.hasNext()){
        	int i = 1;
            JsonNode figure = featuresIterator.next();
            JsonNode coordinates = figure.path("geometry").path("coordinates");
            JsonNode x = coordinates.get(0);
            JsonNode y = coordinates.get(1);
            /*Iterator<JsonNode> coordinatesIterator = coordinates.iterator();
            while(coordinatesIterator.hasNext()){
            	System.out.print(i);
            	i++;
            	pointX = coordinatesIterator.next();
            	pointY = coordinatesIterator.next();
            	//TODO: Make the next line construct Point(x,y) by parsing the X and Y value from JSON point coordinates properly
            }*/
            points.add(new Point(x.asDouble(),y.asDouble()));
        }
        return points;
    }
    
    public ArrayList<ArrayList<Place>> createAreaMap() {
    	ArrayList<ArrayList<Place>> places = new ArrayList<ArrayList<Place>>();
    	
    	for(double shiftY = 51.857411; shiftY > 51.693498 ; shiftY -= 0.000180)
    	{
    		places.add(new ArrayList<Place>());
    		ArrayList<Place> current = places.get(places.size() - 1);
    		for(double shiftX = 19.334416; shiftX < 19.592922; shiftX += 0.000290 )
    		{
    			current.add(new Place(0.0,0.0,0.0,shiftX,shiftY));
    		}
    	}
    	System.out.print("Rozmiar kolumny: " + places.size() + ", Rozmiar wiersza: " + places.get(0).size());
		return places;
    	
    }
    
    public ArrayList<ArrayList<Place>> modifyPlaces(ArrayList<ArrayList<Place>> placesArray,ArrayList<Point> pointsArray) {
    	for(Point point : pointsArray)
    	{
    		for(ArrayList<Place> places : placesArray)
    		{
    			for(Place place: places)
    			{
    				//TODO: create fields X and Y in Place.class
    				if(point.getX() > place.getX() && point.getX() < place.getX() + 0.000180)
        			{
        				if(point.getY() < place.getY() && point.getY() > place.getY() - 0.000290 )
        				{
        					//Eksperymntalne ustawienie wartosci emisji na 1 w obszarze, w ktorym jest jakis punkt
        					place.emission = 1.0;
        					System.out.println("Punkt na "+ place.getX() + ", " + place.getY());
        				}
        			}
    			}
    		}
    	}
    	for(int i=0;i<placesArray.size();i++)
    	{
    		for(int j=0;j<placesArray.get(i).size();j++)
    		{
    			if(placesArray.get(i).get(j).emission == 1.0 ) System.out.println(i +", "+j);
    		}
    	}
    	return placesArray;
    }
}
