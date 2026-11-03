package com.dbit.rwh.controller;

import com.dbit.rwh.dto.TankDto;
import com.dbit.rwh.dto.TankTransactionDto;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.TankService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tanks")
public class TankController {

    private final TankService tankService;
    private final UserRepository userRepo;

    @GetMapping
    public ResponseEntity<List<TankDto>> getTanks(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(tankService.getFarmerTanks(user));
    }

    @PostMapping
    public ResponseEntity<TankDto> createTank(@AuthenticationPrincipal UserDetails principal,
                                              @Valid @RequestBody TankDto dto) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.status(HttpStatus.CREATED).body(tankService.createTank(user, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TankDto> getTank(@AuthenticationPrincipal UserDetails principal,
                                           @PathVariable Long id) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(tankService.getTankById(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTank(@AuthenticationPrincipal UserDetails principal,
                                           @PathVariable Long id) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        tankService.deleteTank(id, user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/transactions")
    public ResponseEntity<TankTransactionDto> addTransaction(@AuthenticationPrincipal UserDetails principal,
                                                             @PathVariable Long id,
                                                             @Valid @RequestBody TankTransactionDto dto) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.status(HttpStatus.CREATED).body(tankService.addTransaction(id, user, dto));
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<TankTransactionDto>> getTransactions(@AuthenticationPrincipal UserDetails principal,
                                                                    @PathVariable Long id) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(tankService.getTransactions(id, user));
    }

    @GetMapping("/{id}/usage")
    public ResponseEntity<Map<String, Double>> getUsage(@AuthenticationPrincipal UserDetails principal,
                                                        @PathVariable Long id) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(tankService.getUsageBreakdown(id, user));
    }

    public TankController(TankService tankService, UserRepository userRepo) {
        this.tankService = tankService;
        this.userRepo = userRepo;
    }

}
