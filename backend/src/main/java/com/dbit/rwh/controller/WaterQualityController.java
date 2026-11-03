package com.dbit.rwh.controller;

import com.dbit.rwh.dto.WaterQualityTestDto;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.WaterQualityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/water-quality")
public class WaterQualityController {

    private final WaterQualityService qualityService;
    private final UserRepository userRepo;

    @PostMapping("/test")
    public ResponseEntity<WaterQualityTestDto> testWater(@AuthenticationPrincipal UserDetails principal,
                                                         @RequestBody WaterQualityTestDto req) {
        if (principal != null) {
            User user = userRepo.findByPhone(principal.getUsername()).orElse(null);
            if (user != null) {
                return ResponseEntity.ok(qualityService.evaluateAndSave(user, req));
            }
        }
        return ResponseEntity.ok(qualityService.evaluate(null, req));
    }

    @GetMapping("/history")
    public ResponseEntity<List<WaterQualityTestDto>> getHistory(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(qualityService.getHistory(user));
    }

    public WaterQualityController(WaterQualityService qualityService, UserRepository userRepo) {
        this.qualityService = qualityService;
        this.userRepo = userRepo;
    }

}
