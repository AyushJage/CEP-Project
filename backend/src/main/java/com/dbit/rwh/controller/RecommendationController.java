package com.dbit.rwh.controller;

import com.dbit.rwh.dto.RecommendationDto;
import com.dbit.rwh.entity.FarmerProfile;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.FarmerProfileRepository;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;
    private final UserRepository userRepo;
    private final FarmerProfileRepository profileRepo;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationDto> generate(@AuthenticationPrincipal UserDetails principal,
                                                       @RequestBody(required = false) Map<String, Object> body,
                                                       @RequestParam(required = false) Double landAreaSqm,
                                                       @RequestParam(required = false) String irrigationSource,
                                                       @RequestParam(required = false) String existingStructure,
                                                       @RequestParam(required = false) String soilType) {
        Double area = landAreaSqm;
        String irrigation = irrigationSource;
        String structure = existingStructure;
        String soil = soilType;
        String district = null;

        if (body != null) {
            if (area == null) {
                Object a = body.get("catchmentAreaSqm");
                if (a == null) a = body.get("landAreaSqm");
                if (a instanceof Number n) area = n.doubleValue();
                else if (a instanceof String s && !s.isBlank()) {
                    try { area = Double.parseDouble(s); } catch (Exception ignored) {}
                }
            }
            if (irrigation == null) {
                Object ir = body.get("currentIrrigationSource");
                if (ir == null) ir = body.get("irrigationSource");
                if (ir != null) irrigation = ir.toString();
            }
            if (structure == null && body.get("existingStructure") != null) {
                structure = body.get("existingStructure").toString();
            }
            if (soil == null && body.get("soilType") != null) {
                soil = body.get("soilType").toString();
            }
            if (body.get("district") != null) {
                district = body.get("district").toString();
            }
        }

        if (principal != null) {
            User user = userRepo.findByPhone(principal.getUsername()).orElse(null);
            if (user != null) {
                FarmerProfile profile = profileRepo.findByUser(user).orElse(null);
                if (area == null) area = (profile != null && profile.getLandAreaSqm() != null) ? profile.getLandAreaSqm() : 8093.0;
                if (irrigation == null) irrigation = (profile != null && profile.getIrrigationSource() != null) ? profile.getIrrigationSource() : "Rain-fed";
                if (structure == null) structure = (profile != null && profile.getRwhStructure() != null) ? profile.getRwhStructure() : "None";
                if (soil == null) soil = (profile != null && profile.getSoilType() != null) ? profile.getSoilType() : "Black (Regur)";
                if (district == null) district = user.getDistrict() != null ? user.getDistrict() : "Pune";

                RecommendationDto dto = recommendationService.generateAndSave(user, area, irrigation, structure, soil, district);
                return ResponseEntity.ok(dto);
            }
        }

        // Guest / unauthenticated fallback
        if (area == null) area = 8093.0;
        if (irrigation == null) irrigation = "Rain-fed";
        if (structure == null) structure = "None";
        if (soil == null) soil = "Black (Regur)";
        if (district == null) district = "Pune";

        RecommendationDto dto = recommendationService.generateRecommendation(area, irrigation, structure, soil, district);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/latest")
    public ResponseEntity<RecommendationDto> getLatest(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        FarmerProfile profile = profileRepo.findByUser(user).orElse(null);

        Double area = (profile != null && profile.getLandAreaSqm() != null) ? profile.getLandAreaSqm() : 8093.0;
        String irrigation = (profile != null && profile.getIrrigationSource() != null) ? profile.getIrrigationSource() : "Rain-fed";
        String structure = (profile != null && profile.getRwhStructure() != null) ? profile.getRwhStructure() : "None";
        String soil = (profile != null && profile.getSoilType() != null) ? profile.getSoilType() : "Black (Regur)";

        return ResponseEntity.ok(recommendationService.generateRecommendation(area, irrigation, structure, soil, user.getDistrict()));
    }

    public RecommendationController(RecommendationService recommendationService, UserRepository userRepo, FarmerProfileRepository profileRepo) {
        this.recommendationService = recommendationService;
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
    }

}
