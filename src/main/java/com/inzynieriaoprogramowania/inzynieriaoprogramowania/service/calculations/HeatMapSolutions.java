package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations;

public class HeatMapSolutions {
    public double[][] heatArray;

    public HeatMapSolutions(double[][] heatArray) {
        this.heatArray = heatArray;
    }

    //copy data not reference
    public void overwrite(HeatMapSolutions heatMap) {
        for(int i = 0; i < heatArray.length; i++){
            for(int j = 0; j < heatArray[i].length; j++){
                this.heatArray[i][j] = heatMap.heatArray[i][j];
            }
        }
    }

    public int getWidth(){return heatArray.length;}
    public int getHeight(){return heatArray[0].length;}
}
