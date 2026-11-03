package com.dbit.rwh.controller;

import com.dbit.rwh.dto.MaintenanceTaskDto;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance")
public class MaintenanceController {

    private final AlertService alertService;
    private final UserRepository userRepo;

    @GetMapping
    public ResponseEntity<List<MaintenanceTaskDto>> getTasks(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(alertService.getMaintenanceTasks(user));
    }

    @PatchMapping("/{id}/done")
    public ResponseEntity<Void> markDone(@AuthenticationPrincipal UserDetails principal,
                                         @PathVariable Long id) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        alertService.markTaskDone(id, user);
        return ResponseEntity.ok().build();
    }

    public MaintenanceController(AlertService alertService, UserRepository userRepo) {
        this.alertService = alertService;
        this.userRepo = userRepo;
    }

}
