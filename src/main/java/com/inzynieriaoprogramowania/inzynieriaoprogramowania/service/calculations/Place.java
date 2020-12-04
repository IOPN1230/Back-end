package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations;

public class Place {
    public double emission;
    public double heatConduction;
    public double heatDecline;

    public Place(double emission, double heatConduction, double heatDecline) {
        this.emission = emission;
        this.heatConduction = heatConduction;
        this.heatDecline = heatDecline;
    }
}
