package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

public class Point {

	double x,y;
	double emission, heatConduction, heatDecline;
	public Point(double x, double y, double emission, double heatConduction, double heatDecline) {
		this.x = x;
		this.y = y;
		this.emission = emission;
		this.heatConduction = heatConduction;
		this.heatDecline = heatDecline;
		
	}
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
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
