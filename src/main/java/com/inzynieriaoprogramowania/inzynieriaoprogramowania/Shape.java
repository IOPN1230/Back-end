package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import java.util.ArrayList;

public class Shape {
    private ArrayList<Double> coordinatesX = new ArrayList<>();
    private ArrayList<Double> coordinatesY = new ArrayList<>();

    public ArrayList<Double> getCoordinatesX() {
        return coordinatesX;
    }

    public void setCoordinatesX(ArrayList<Double> coordinatesX) {
        this.coordinatesX = coordinatesX;
    }

    public ArrayList<Double> getCoordinatesY() {
        return coordinatesY;
    }

    public void setCoordinatesY(ArrayList<Double> coordinatesY) {
        this.coordinatesY = coordinatesY;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < coordinatesX.size(); i++){
            res.append("\nx = ").append(coordinatesX.get(i)).append("\n");
            res.append("\ny = ").append(coordinatesY.get(i)).append("\n");
        }
        return res.toString();
    }
}
