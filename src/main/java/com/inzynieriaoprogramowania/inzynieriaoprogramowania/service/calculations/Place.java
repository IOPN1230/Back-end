package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations;

public class Place {
    public double emission;
    public double heatConduction;
    public double heatDecline;
    public double x;
    public double y;

    public Place(double emission, double heatConduction, double heatDecline, double x, double y) {
        this.emission = emission;
        this.heatConduction = heatConduction;   //higher value = slower heat conduction
        this.heatDecline = heatDecline;
        this.x = x;
        this.y = y;
    }
    
    public double getX()
    {
    	return x;
    }
    public double getY()
    {
    	return y;
    }
}
