package com.inzynieriaoprogramowania.inzynieriaoprogramowania.controller;

import com.inzynieriaoprogramowania.inzynieriaoprogramowania.service.HeatMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(
        value = "/api/heat-map",
        produces = MediaType.IMAGE_JPEG_VALUE,
        method = RequestMethod.POST
)
public class HeatMapController {

    @Autowired
    private HeatMapService heatMapService;

    @PostMapping("/")
    public @ResponseBody byte[] getHeatMap(HttpServletRequest httpServletRequest) {
        byte[] res = heatMapService.getHeatMap(httpServletRequest);
        return res;
    }
}
