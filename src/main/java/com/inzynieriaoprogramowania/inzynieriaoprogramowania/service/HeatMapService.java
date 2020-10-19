package com.inzynieriaoprogramowania.inzynieriaoprogramowania.service;

import org.springframework.stereotype.Service;

@Service
public class HeatMapService {

    public String getHeatMap(int id) {
        return "...przeliczam dla obiektu: " + id + "...zwracam bitmapę";
    }
}
