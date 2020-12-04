package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations;

public class Stage {
    public int stepCount;
    public double precisionFactor;
    public double accuracy;

    public Stage(int stepCount, double precisionFactor, double accuracy) {
        this.stepCount = stepCount;
        this.precisionFactor = precisionFactor;
        this.accuracy = accuracy;
    }
}
