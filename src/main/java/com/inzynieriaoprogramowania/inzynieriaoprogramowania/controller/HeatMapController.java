package com.inzynieriaoprogramowania.inzynieriaoprogramowania.controller;

import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.HeatMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/heat-map")
public class HeatMapController {

    @Autowired
    private HeatMapService heatMapService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getHeatMap(@PathVariable int id, HttpServletRequest httpServletRequest) {
        return heatMapService.getHeatMap(id, httpServletRequest);
    }
}
