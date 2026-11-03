package com.dbit.rwh.controller;

import com.dbit.rwh.dto.FertilityAssessRequest;
import com.dbit.rwh.dto.FertilityReportDto;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.FertilityService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fertility")
public class FertilityController {

    private final FertilityService fertilityService;
    private final UserRepository userRepo;

    @PostMapping("/assess")
    public ResponseEntity<FertilityReportDto> assess(@AuthenticationPrincipal UserDetails principal,
                                                     @RequestBody FertilityAssessRequest req) {
        if (principal != null) {
            User user = userRepo.findByPhone(principal.getUsername()).orElse(null);
            if (user != null) {
                return ResponseEntity.ok(fertilityService.assessAndSave(user, req));
            }
        }
        return ResponseEntity.ok(fertilityService.assess(req));
    }

    @GetMapping("/history")
    public ResponseEntity<List<FertilityReportDto>> getHistory(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(fertilityService.getFarmerHistory(user));
    }

    public FertilityController(FertilityService fertilityService, UserRepository userRepo) {
        this.fertilityService = fertilityService;
        this.userRepo = userRepo;
    }

}
