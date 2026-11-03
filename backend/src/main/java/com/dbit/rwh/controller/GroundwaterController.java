package com.dbit.rwh.controller;

import com.dbit.rwh.service.GroundwaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/groundwater")
public class GroundwaterController {

    private final GroundwaterService gwService;

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getSummary(@RequestParam(defaultValue = "Pune") String district) {
        return ResponseEntity.ok(gwService.getDistrictSummary(district));
    }

    @GetMapping("/wells")
    public ResponseEntity<List<Map<String, Object>>> getWells(@RequestParam(defaultValue = "Pune") String district) {
        return ResponseEntity.ok(gwService.getWellPins(district));
    }

    public GroundwaterController(GroundwaterService gwService) {
        this.gwService = gwService;
    }

}
