package com.dbit.rwh.controller;

import com.dbit.rwh.dto.RegisterRequest;
import com.dbit.rwh.dto.UserDto;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.FieldWorkerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assist")
@PreAuthorize("hasAnyRole('FIELD_WORKER', 'ADMIN')")
public class AssistController {

    private final FieldWorkerService fieldWorkerService;
    private final UserRepository userRepo;

    @PostMapping("/farmers")
    public ResponseEntity<UserDto> registerFarmer(@AuthenticationPrincipal UserDetails principal,
                                                  @Valid @RequestBody RegisterRequest req) {
        User worker = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        UserDto dto = fieldWorkerService.registerFarmerOnBehalf(worker, req);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/farmers")
    public ResponseEntity<List<UserDto>> getAssignedFarmers(@AuthenticationPrincipal UserDetails principal) {
        User worker = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        return ResponseEntity.ok(fieldWorkerService.getAssignedFarmers(worker));
    }

    @GetMapping("/farmer-lookup")
    public ResponseEntity<UserDto> lookupFarmer(@RequestParam String phone) {
        return ResponseEntity.ok(fieldWorkerService.findFarmerByPhone(phone));
    }

    public AssistController(FieldWorkerService fieldWorkerService, UserRepository userRepo) {
        this.fieldWorkerService = fieldWorkerService;
        this.userRepo = userRepo;
    }

}
