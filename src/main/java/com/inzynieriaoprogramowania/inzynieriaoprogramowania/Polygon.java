package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import java.util.ArrayList;

public class Polygon {
	ArrayList<Point> vertexes;
	ArrayList<Equation> equations;
	ArrayList<Double> extremes;
	double emission, heatConduction, heatDecline;
	
	public Polygon(ArrayList<Point> vertexes, double emission, double heatConduction, double heatDecline) {
		this.vertexes = new ArrayList<>();
		equations = new ArrayList<>();
		extremes = new ArrayList<Double>();
		this.vertexes = vertexes;
		this.emission = emission;
		this.heatConduction = heatConduction;
		this.heatDecline = heatDecline;
		createEquations();
		setExtremes();
	}
	
	private void createEquations()
	{
		for(int i = 0; i < this.vertexes.size()-1 ; i++) {
			double a = (float) (( vertexes.get(i).getY() - vertexes.get(i+1).getY() )/( vertexes.get(i).getX() - vertexes.get(i+1).getX() ));
			if(a == 0) a = 0.01;
			double b = (float) (( vertexes.get(i).getY() - a * vertexes.get(i).getX()));
			equations.add(new Equation(a,b,vertexes.get(i).getX(),vertexes.get(i+1).getX()));
			
		}
	}
	
	public ArrayList<Equation> getEquations(){
		return this.equations;
	}
	
	private void setExtremes() {
		double maxTop = vertexes.get(0).getY();
		double maxLeft = vertexes.get(0).getX();
		double maxBottom = vertexes.get(0).getY();
		double maxRight = vertexes.get(0).getX();
		for(Point p : vertexes)
		{
			if(p.getX() < maxLeft) maxLeft = p.getX();
			if(p.getX() > maxRight) maxRight = p.getX();
			if(p.getY() < maxBottom) maxBottom = p.getY();
			if(p.getY() > maxTop) maxTop = p.getY();
		}
		extremes.add(maxTop);
		extremes.add(maxLeft);
		extremes.add(maxBottom);
		extremes.add(maxRight);
	}
	
	public ArrayList<Double> getExtremes(){
		return extremes;
	}

	public double getEmission() {
		return emission;
	}

	public double getHeatConduction() {
		return heatConduction;
	}

	public double getHeatDecline() {
		return heatDecline;
	}
	

}
