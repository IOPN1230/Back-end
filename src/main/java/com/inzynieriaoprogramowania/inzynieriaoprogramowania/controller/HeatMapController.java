package com.inzynieriaoprogramowania.inzynieriaoprogramowania.controller;

import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.HeatMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/heat-map")
public class HeatMapController {

    @Autowired
    private HeatMapService heatMapService;

    @GetMapping("/{id}")
    public String getHeatMap(@PathVariable int id) {
        return heatMapService.getHeatMap(id);
    }
}
