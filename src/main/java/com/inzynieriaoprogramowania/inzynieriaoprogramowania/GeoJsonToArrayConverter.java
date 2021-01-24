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
        JsonNode features = map.path("features");
        Iterator<JsonNode> featuresIterator = features.iterator();
        while(featuresIterator.hasNext()){
            JsonNode figure = featuresIterator.next();
            JsonNode coordinates = figure.path("geometry").path("coordinates");
            Iterator<JsonNode> coordinatesIterator = coordinates.iterator();
            Shape shape = new Shape();
            boolean isX = true;
            while(coordinatesIterator.hasNext()){
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

            if(type.asText().equals("Point"))
            {
            	JsonNode properties = figure.path("properties");
            	JsonNode coordinates = figure.path("geometry").path("coordinates");
            	JsonNode x = coordinates.get(0);
            	JsonNode y = coordinates.get(1);
            	Double emission = properties.get("emission").asDouble();
            	Double heatConduction = properties.get("heatConducton").asDouble();
            	Double heatDecline = properties.get("heatDecline").asDouble();
            	points.add(new Point(x.asDouble(),y.asDouble(),emission,heatConduction,heatDecline));
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
        	vertexesArray.clear();
            JsonNode figure = featuresIterator.next();
            JsonNode type = figure.path("geometry").path("type");
            JsonNode geometry = figure.path("geometry");
            if(type.asText().equals("Polygon"))
            {
           
            	JsonNode coordinates = geometry.path("coordinates").get(0);
            	JsonNode properties = figure.path("properties");
            	Double emission = (double) 0; 
            	Double heatConduction = (double) 0;
            	Double heatDecline = (double) 0;
            	Iterator<JsonNode> coordinatesIterator = coordinates.iterator();
            	for(int i= 0;i<coordinates.size();i++)
            	{
           
            		double x = coordinates.get(i).get(0).asDouble();
            		double y = coordinates.get(i).get(1).asDouble();
            		emission = properties.get("emission").asDouble();
                	heatConduction = properties.get("heatConducton").asDouble();
                	heatDecline = properties.get("heatDecline").asDouble();
            		vertexesArray.add(new Point(x,y,emission,heatConduction,heatDecline));
            	}
            
                polygons.add(new Polygon(vertexesArray,emission,heatConduction,heatDecline));
            }


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
    			current.add(new Place(0.0,0.5,0.1,shiftX,shiftY));
    		}
    	}
    	//System.out.print("Rozmiar kolumny: " + places.size() + ", Rozmiar wiersza: " + places.get(0).size());
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
        					place.emission = point.getEmission();
        					place.heatConduction = point.getHeatConduction();
        					place.heatDecline = point.getHeatDecline();
        				
        					//System.out.println("Punkt na "+ place.getX() + ", " + place.getY());
        				}
        			}
    			}
    		}
    	}
    	
    	for(Polygon polygon: polygonsArray)
    	{
    		//System.out.println(polygon.getExtremes());
    		//Znajdz skrajne places
    		int startX = 0, startY= 0, endX= 0, endY= 0;
    		for(int i =0;i<placesArray.size();i++)
    		{
    			for(int j = 0; j < placesArray.get(i).size(); j++)
    			{
    				Place p = placesArray.get(i).get(j);

    				if(p.getY() <= polygon.getExtremes().get(0) && polygon.getExtremes().get(0) - 0.000180 <= p.getY() )
					{
						startY = i;
					}
    				if(p.getX() >= polygon.getExtremes().get(1) && polygon.getExtremes().get(1) + 0.000290 >= p.getX() )
    				{
    					startX = j;
    				}
    				if(p.getY() >= polygon.getExtremes().get(2) && polygon.getExtremes().get(2) + 0.000180 >= p.getY())
					{
						endY = i;
					}
    				if(p.getX() <= polygon.getExtremes().get(3) && polygon.getExtremes().get(3) - 0.000290 <= p.getX())
    				{
    					endX = j;
    				}

    			}
    		}
    		ArrayList<Equation> checkingEquations = new ArrayList<>();
    		//Na sztywno wpisana dziedzina X naszych rownan sprawdzajacych, ustawiona jest ona od 18 do 21 ( nasze dlugosci geograficzne)
    		
    		for(int i=0; i<360 ;i = i+8)
    		{
    			double radians = Math.toRadians(i);
    			checkingEquations.add(new Equation(Math.tan(radians),0,18,21));
        		checkingEquations.add(new Equation(Math.tan(radians),0,18,21));
    		}
    		//Dla kazdego punktu w obszarze sprawdzaj czy sie znajduje
    		int counter = 0;
    		for(int yIterator = startY; yIterator < endY; yIterator ++)
    		{
    			for(int xIterator = startX; xIterator<endX;xIterator++)
    			{
    				Place p = placesArray.get(yIterator).get(xIterator);
    				boolean isPointInside = true;
    				
    				for(int i=0; i<checkingEquations.size();i++)
    				{
    					checkingEquations.get(i).setB(p);
    					if(i % 2 == 0) checkingEquations.get(i).setDomain(18, p.getX());
    					else checkingEquations.get(i).setDomain(p.getX(),21);
    				}
    			
    	    		
    	    		for(Equation ce : checkingEquations)
    	    		{
    	    			boolean hasCrossedAny = false;
    	    			for(Equation e : polygon.getEquations())
    	    			{
    	    				if(ce.check(e)) hasCrossedAny = true;

    	    			}
    	    			if(!hasCrossedAny) isPointInside = false;
    	    		}
    	    	
    	    		if(isPointInside)
    	    		{
    	    			p.emission += polygon.getEmission();
						p.heatConduction = polygon.getHeatConduction();
						p.heatDecline = polygon.getHeatDecline();
    	    		}

    			}
    		
    		}
    	}
    	return placesArray;
    }
    
}
