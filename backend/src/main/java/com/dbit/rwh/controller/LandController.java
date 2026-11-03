package com.dbit.rwh.controller;

import com.dbit.rwh.dto.LandDataDto;
import com.dbit.rwh.entity.LandData;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.LandDataRepository;
import com.dbit.rwh.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/land")
public class LandController {

    private final LandDataRepository landRepo;
    private final UserRepository userRepo;

    @GetMapping
    public ResponseEntity<List<LandDataDto>> getLands(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        List<LandDataDto> list = landRepo.findAllByFarmer(user).stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<LandDataDto> addLand(@AuthenticationPrincipal UserDetails principal,
                                               @Valid @RequestBody LandDataDto dto) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        LandData land = LandData.builder()
                .farmer(user)
                .plotName(dto.getPlotName())
                .areaSqm(dto.getAreaSqm())
                .surfaceType(dto.getSurfaceType())
                .village(dto.getVillage() != null ? dto.getVillage() : user.getVillage())
                .taluka(dto.getTaluka() != null ? dto.getTaluka() : user.getTaluka())
                .district(dto.getDistrict() != null ? dto.getDistrict() : user.getDistrict())
                .notes(dto.getNotes())
                .build();
        landRepo.save(land);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(land));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LandDataDto> updateLand(@AuthenticationPrincipal UserDetails principal,
                                                  @PathVariable Long id,
                                                  @Valid @RequestBody LandDataDto dto) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        LandData land = landRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Land plot not found"));
        if (!land.getFarmer().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }

        land.setPlotName(dto.getPlotName());
        land.setAreaSqm(dto.getAreaSqm());
        land.setSurfaceType(dto.getSurfaceType());
        land.setNotes(dto.getNotes());
        landRepo.save(land);
        return ResponseEntity.ok(toDto(land));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLand(@AuthenticationPrincipal UserDetails principal,
                                           @PathVariable Long id) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        LandData land = landRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Land plot not found"));
        if (!land.getFarmer().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        landRepo.delete(land);
        return ResponseEntity.noContent().build();
    }

    private LandDataDto toDto(LandData l) {
        return LandDataDto.builder()
                .id(l.getId())
                .plotName(l.getPlotName())
                .areaSqm(l.getAreaSqm())
                .surfaceType(l.getSurfaceType())
                .village(l.getVillage())
                .taluka(l.getTaluka())
                .district(l.getDistrict())
                .notes(l.getNotes())
                .build();
    }

    public LandController(LandDataRepository landRepo, UserRepository userRepo) {
        this.landRepo = landRepo;
        this.userRepo = userRepo;
    }

}
