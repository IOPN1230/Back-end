package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations;


public class AreaMap {
    private Place[][] area;
    private HeatMapSolutions heatMap;

    public AreaMap(Place[][] area, HeatMapSolutions heatMap) {
        this.area = area;
        this.heatMap = heatMap;
        // TODO: throw custom exception if area and heat map has different size
    }

    public Place[][] getArea() {
        return area;
    }

    public HeatMapSolutions getHeatMap() {
        return heatMap;
    }

    public void setArea(Place[][] area) {
        // TODO: throw custom exception if area and heat map has different size
        this.area = area;
    }

    public void setHeatMap(HeatMapSolutions heatMap) {
        // TODO: throw custom exception if area and heat map has different size
        this.heatMap = heatMap;
    }
}
