package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations;

public class HeatMapAnalyser {
    private Configuration configuration;

    public HeatMapAnalyser(Configuration configuration) {
        this.configuration = configuration;
    }

    public HeatMapSolutions calculateHeatMap(AreaMap areaMap){
        //TODO:implement this function
        int sizeX = areaMap.getArea().length;
        int sizeY = areaMap.getArea()[0].length;
        return new HeatMapSolutions(new double[sizeX][sizeY]);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
