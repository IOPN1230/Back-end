package com.inzynieriaoprogramowania.inzynieriaoprogramowania.controller;

import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.HeatMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(
        value = "/api/heat-map",
        produces = MediaType.IMAGE_JPEG_VALUE
)
public class HeatMapController {

    @Autowired
    private HeatMapService heatMapService;

    @GetMapping("/{id}")
    public @ResponseBody byte[] getHeatMap(@PathVariable int id, HttpServletRequest httpServletRequest) {
        return heatMapService.getHeatMap(id, httpServletRequest);
    }
}
