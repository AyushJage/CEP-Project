package com.dbit.rwh.controller;

import com.dbit.rwh.dto.AlertDto;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.AlertService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final AlertService alertService;
    private final UserRepository userRepo;

    @GetMapping
    public ResponseEntity<List<AlertDto>> getAlerts(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(alertService.getFarmerAlerts(user));
    }

    @GetMapping("/unread-count")
    public ResponseEntity<Map<String, Long>> getUnreadCount(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(Map.of("unreadCount", alertService.getUnreadCount(user)));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markRead(@AuthenticationPrincipal UserDetails principal,
                                         @PathVariable Long id) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        alertService.markAlertRead(id, user);
        return ResponseEntity.ok().build();
    }

    public AlertController(AlertService alertService, UserRepository userRepo) {
        this.alertService = alertService;
        this.userRepo = userRepo;
    }

}
