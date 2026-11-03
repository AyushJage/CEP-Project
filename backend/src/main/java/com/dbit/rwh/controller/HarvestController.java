package com.dbit.rwh.controller;

import com.dbit.rwh.dto.HarvestCalculateRequest;
import com.dbit.rwh.dto.HarvestCalculateResponse;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.HarvestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/harvest")
public class HarvestController {

    private final HarvestService harvestService;
    private final UserRepository userRepo;

    @PostMapping("/calculate")
    public ResponseEntity<HarvestCalculateResponse> calculate(@AuthenticationPrincipal UserDetails principal,
                                                              @Valid @RequestBody HarvestCalculateRequest req) {
        if (principal != null) {
            User user = userRepo.findByPhone(principal.getUsername()).orElse(null);
            if (user != null) {
                return ResponseEntity.ok(harvestService.calculateAndSave(user, req));
            }
        }
        return ResponseEntity.ok(harvestService.calculate(req));
    }

    @GetMapping("/history")
    public ResponseEntity<List<HarvestCalculateResponse>> getHistory(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(harvestService.getFarmerHistory(user));
    }

    public HarvestController(HarvestService harvestService, UserRepository userRepo) {
        this.harvestService = harvestService;
        this.userRepo = userRepo;
    }

}
