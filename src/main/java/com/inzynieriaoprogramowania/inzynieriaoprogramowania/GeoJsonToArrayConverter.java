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
            JsonNode type = figure.path("geometry").path("type");
            if(type.equals("Point"))
            {
            	JsonNode coordinates = figure.path("geometry").path("coordinates");
            	JsonNode x = coordinates.get(0);
            	JsonNode y = coordinates.get(1);
            	points.add(new Point(x.asDouble(),y.asDouble()));
            }
            if(type.equals("Polygon"))
            {
            	JsonNode coordinates = figure.path("geometry").path("coordinates");
            	
            }
            
        }
        return points;
    }
    
    public ArrayList<Polygon> getAllPolygons(JsonNode map)
    {
    	ArrayList<Polygon> polygons = new ArrayList<>();
    	ArrayList<Point> vertexesArray = new ArrayList<>();
    
        JsonNode features = map.path("features");
        Iterator<JsonNode> featuresIterator = features.iterator();
        while(featuresIterator.hasNext()){
        	int i = 1;
            JsonNode figure = featuresIterator.next();
            JsonNode type = figure.path("geometry").path("type");
            JsonNode geometry = figure.path("geometry");
            if(type.equals("Polygon"))
            {
            	JsonNode coordinates = figure.path("coordinates");
            	Iterator<JsonNode> coordinatesIterator = coordinates.iterator();
                while(coordinatesIterator.hasNext())
                {
                	JsonNode particularCoordinates = coordinatesIterator.next();
                	JsonNode x = particularCoordinates.get(0);
                	JsonNode y = particularCoordinates.get(1);
                	vertexesArray.add(new Point(x.asDouble(),y.asDouble()));
                }
            }    
            polygons.add(new Polygon(vertexesArray));
        }
        return polygons;
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
    
    public ArrayList<ArrayList<Place>> modifyPlaces(ArrayList<ArrayList<Place>> placesArray,ArrayList<Point> pointsArray,ArrayList<Polygon> polygonsArray) {
    	for(Point point : pointsArray)
    	{
    		for(ArrayList<Place> places : placesArray)
    		{
    			for(Place place: places)
    			{
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
    	
    	for(Polygon polygon: polygonsArray)
    	{
    		//Lista przechowujaca rownania sprawdzajace zawieranie sie punktu wewnatrz wielokata
    		ArrayList<Equation> checkingEquations = new ArrayList<>();
 
    		//for(int i=0;i<polygon.getEquations().size();i++)
    		//{
    			//Tutaj bedzie caly algorytm wyznaczania obecnosci wielokata w danym elemencie MapArraya
    			//Zwroci on po prostu uaktualniona wersje placesArray, gotowa do wyslania do obliczen
    		//}
    		
    	}
    	return placesArray;
    }
    
}
