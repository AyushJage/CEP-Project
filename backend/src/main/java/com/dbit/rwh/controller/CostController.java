package com.dbit.rwh.controller;

import com.dbit.rwh.dto.CostCalculateRequest;
import com.dbit.rwh.dto.CostCalculateResponse;
import com.dbit.rwh.service.CostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cost")
public class CostController {

    private final CostService costService;

    @PostMapping("/calculate")
    public ResponseEntity<CostCalculateResponse> calculate(@Valid @RequestBody CostCalculateRequest req) {
        return ResponseEntity.ok(costService.calculateSavings(req));
    }

    public CostController(CostService costService) {
        this.costService = costService;
    }

}
