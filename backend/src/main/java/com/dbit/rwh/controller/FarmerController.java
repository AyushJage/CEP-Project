package com.dbit.rwh.controller;

import com.dbit.rwh.dto.FarmerProfileDto;
import com.dbit.rwh.entity.FarmerProfile;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.FarmerProfileRepository;
import com.dbit.rwh.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/farmers")
public class FarmerController {

    private final UserRepository userRepo;
    private final FarmerProfileRepository profileRepo;

    @GetMapping("/me")
    public ResponseEntity<FarmerProfileDto> getProfile(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        FarmerProfile profile = profileRepo.findByUser(user)
                .orElse(FarmerProfile.builder().user(user).build());

        return ResponseEntity.ok(toDto(profile));
    }

    @PutMapping("/me")
    public ResponseEntity<FarmerProfileDto> updateProfile(@AuthenticationPrincipal UserDetails principal,
                                                          @RequestBody FarmerProfileDto dto) {
        User user = userRepo.findByPhone(principal.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        FarmerProfile profile = profileRepo.findByUser(user)
                .orElse(FarmerProfile.builder().user(user).build());

        profile.setLandAreaSqm(dto.getLandAreaSqm());
        profile.setIrrigationSource(dto.getIrrigationSource());
        profile.setRwhStructure(dto.getRwhStructure());
        profile.setMainCrop(dto.getMainCrop());
        profile.setSoilType(dto.getSoilType());
        profileRepo.save(profile);

        return ResponseEntity.ok(toDto(profile));
    }

    private FarmerProfileDto toDto(FarmerProfile p) {
        return FarmerProfileDto.builder()
                .landAreaSqm(p.getLandAreaSqm())
                .irrigationSource(p.getIrrigationSource())
                .rwhStructure(p.getRwhStructure())
                .mainCrop(p.getMainCrop())
                .soilType(p.getSoilType())
                .assignedFieldWorkerId(p.getAssignedFieldWorker() != null ? p.getAssignedFieldWorker().getId() : null)
                .assignedFieldWorkerName(p.getAssignedFieldWorker() != null ? p.getAssignedFieldWorker().getName() : null)
                .build();
    }

    public FarmerController(UserRepository userRepo, FarmerProfileRepository profileRepo) {
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
    }

}
