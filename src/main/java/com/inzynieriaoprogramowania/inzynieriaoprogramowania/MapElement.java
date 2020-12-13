package com.inzynieriaoprogramowania.inzynieriaoprogramowania;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Map;

public class MapElement {
    //TODO: Add all necessary elements from geoJSON
    private String type;
    private String[] featuresType;
    private String[] geometryType;

    public String[] getFeaturesType() {
        return featuresType;
    }

    public String[] getGeometryType() {
        return geometryType;
    }

    public String getType() {
        return type;
    }
}
