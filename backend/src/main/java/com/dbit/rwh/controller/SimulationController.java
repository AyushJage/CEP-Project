package com.dbit.rwh.controller;

import com.dbit.rwh.dto.SimulationRequest;
import com.dbit.rwh.dto.SimulationResponse;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.SimulationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

    private final SimulationService simulationService;
    private final UserRepository userRepo;

    @PostMapping("/tank")
    public ResponseEntity<SimulationResponse> simulate(@AuthenticationPrincipal UserDetails principal,
                                                       @Valid @RequestBody SimulationRequest req) {
        User user = (principal != null) ? userRepo.findByPhone(principal.getUsername()).orElse(null) : null;
        return ResponseEntity.ok(simulationService.runSimulation(user, req));
    }

    public SimulationController(SimulationService simulationService, UserRepository userRepo) {
        this.simulationService = simulationService;
        this.userRepo = userRepo;
    }

}
