package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import java.util.ArrayList;

public class Polygon {
	ArrayList<Point> vertexes;
	ArrayList<Equation> equations;
	
	public Polygon(ArrayList<Point> vertexes) {
		vertexes = new ArrayList<>();
		equations = new ArrayList<>();
		this.vertexes = vertexes;
		createEquations();
	}
	
	private void createEquations()
	{
		for(int i = 0; i < this.vertexes.size()-1 ; i++) {
			float a = (float) (( vertexes.get(i).getY() - vertexes.get(i+1).getY() )/( vertexes.get(i).getX() - vertexes.get(i+1).getX() ));
			float b = (float) (( vertexes.get(i).getY() - a * vertexes.get(i).getX()));
			equations.add(new Equation(a,b));
			
		}
	}
	

}
