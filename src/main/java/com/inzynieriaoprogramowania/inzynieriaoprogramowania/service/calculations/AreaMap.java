package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations;


public class AreaMap {
    private Place[][] area;
    private HeatMapSolutions heatMap;

    public AreaMap(Place[][] area, HeatMapSolutions heatMap) {
        this.area = area;
        this.heatMap = heatMap;
    }

    public Place[][] getArea() {
        return area;
    }

    public HeatMapSolutions getHeatMap() {
        return heatMap;
    }

    public void setArea(Place[][] area) {
        this.area = area;
    }

    public void setHeatMap(HeatMapSolutions heatMap) {
        this.heatMap = heatMap;
    }

    public int getWidth(){return area.length;}
    public int getHeight(){return area[0].length;}

    public boolean validate(){
        int height = this.getHeight();
        for (Place[] column : area) {
            if (column.length != height) {
                return true;
            }
        }
        return heatMap.validate();
    }
}
