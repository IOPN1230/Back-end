package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.calculations;

public class HeatMapAnalyser {
    private Configuration configuration;

    public HeatMapAnalyser(Configuration configuration) {
        this.configuration = configuration;
    }

    public HeatMapSolutions calculateHeatMap(AreaMap areaMap){
        HeatMapSolutions oldHeatMap = new HeatMapSolutions(new double[areaMap.getArea().length][areaMap.getArea()[0].length]);
        HeatMapSolutions newHeatMap = new HeatMapSolutions(new double[areaMap.getArea().length][areaMap.getArea()[0].length]);
        oldHeatMap.overwrite(areaMap.getHeatMap());
        newHeatMap.overwrite(oldHeatMap);
        for (Stage stage:configuration.stages) {
            for (int stepNum = 0; stepNum < stage.stepCount; stepNum++) {

                //adding emission to newHeatMap
                for(int i = 0; i < newHeatMap.getWidth(); i++){
                    for(int j = 0; j < newHeatMap.getHeight(); j++){
                        newHeatMap.heatArray[i][j] += areaMap.getArea()[i][j].emission / stage.precisionFactor;
                    }
                }

                //calculating heat conduction vertically
                for(int i = 0; i < newHeatMap.getWidth(); i++) {
                    for (int j = 0; j < newHeatMap.getHeight() - 1; j++) {
                        double change = (oldHeatMap.heatArray[i][j] - oldHeatMap.heatArray[i][j + 1])
                                / stage.precisionFactor / areaMap.getArea()[i][j].heatConduction
                                / areaMap.getArea()[i][j + 1].heatConduction;
                        newHeatMap.heatArray[i][j] -= change;
                        newHeatMap.heatArray[i][j + 1] += change;
                    }
                }

                //calculating heat conduction horizontally
                for(int i = 0; i < newHeatMap.getWidth() - 1; i++) {
                    for (int j = 0; j < newHeatMap.getHeight(); j++) {
                        double change = (oldHeatMap.heatArray[i][j] - oldHeatMap.heatArray[i + 1][j])
                                / stage.precisionFactor / areaMap.getArea()[i][j].heatConduction
                                / areaMap.getArea()[i + 1][j].heatConduction;
                        newHeatMap.heatArray[i][j] -= change;
                        newHeatMap.heatArray[i + 1][j] += change;
                    }
                }

                //calculating heat decline
                for(int i = 0; i < newHeatMap.getWidth(); i++){
                    for(int j = 0; j < newHeatMap.getHeight(); j++){
                        newHeatMap.heatArray[i][j] *= 1 - areaMap.getArea()[i][j].heatDecline * stage.precisionFactor;
                    }
                }

                oldHeatMap.overwrite(newHeatMap);
                //TODO: check accuracy
            }
        }

        return oldHeatMap;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
