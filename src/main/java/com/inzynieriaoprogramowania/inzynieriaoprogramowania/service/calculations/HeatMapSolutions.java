package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations;

public class HeatMapSolutions {
    public double[][] heatArray;

    public HeatMapSolutions(double[][] heatArray) {
        this.heatArray = heatArray;
    }

    //copy data not reference
    public void overwrite(HeatMapSolutions heatMap) {
        for(int i = 0; i < heatArray.length; i++){
            System.arraycopy(heatMap.heatArray[i], 0, this.heatArray[i], 0, heatArray[i].length);
        }
    }

    public boolean validate(){
        int height = this.getHeight();
        for (double[] column : heatArray) {
            if (column.length != height) {
                return true;
            }
        }
        return false;
    }

    public int getWidth(){return heatArray.length;}
    public int getHeight(){return heatArray[0].length;}
}
