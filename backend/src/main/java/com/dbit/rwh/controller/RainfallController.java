package com.dbit.rwh.controller;

import com.dbit.rwh.dto.RainfallRecordDto;
import com.dbit.rwh.entity.DistrictRainfallNormal;
import com.dbit.rwh.entity.HistoricalRainfall;
import com.dbit.rwh.entity.RainfallRecord;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.DistrictRainfallNormalRepository;
import com.dbit.rwh.repository.HistoricalRainfallRepository;
import com.dbit.rwh.repository.RainfallRecordRepository;
import com.dbit.rwh.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/rainfall")
public class RainfallController {

    private final RainfallRecordRepository rainfallRepo;
    private final UserRepository userRepo;
    private final HistoricalRainfallRepository historicalRepo;
    private final DistrictRainfallNormalRepository normalRepo;

    @GetMapping
    public ResponseEntity<List<RainfallRecordDto>> getRecords(@AuthenticationPrincipal UserDetails principal) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        List<RainfallRecordDto> list = rainfallRepo.findAllByFarmerOrderByRecordDateDesc(user).stream()
                .map(this::toDto)
                .toList();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<RainfallRecordDto> addRecord(@AuthenticationPrincipal UserDetails principal,
                                                       @Valid @RequestBody RainfallRecordDto dto) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        RainfallRecord r = RainfallRecord.builder()
                .farmer(user)
                .recordDate(dto.getRecordDate())
                .rainfallMm(dto.getRainfallMm())
                .district(dto.getDistrict() != null ? dto.getDistrict() : user.getDistrict())
                .taluka(dto.getTaluka() != null ? dto.getTaluka() : user.getTaluka())
                .note(dto.getNote())
                .build();
        rainfallRepo.save(r);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDto(r));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@AuthenticationPrincipal UserDetails principal,
                                             @PathVariable Long id) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        RainfallRecord r = rainfallRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Record not found"));
        if (!r.getFarmer().getId().equals(user.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        rainfallRepo.delete(r);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<Map<String, Object>> getSummary(@AuthenticationPrincipal UserDetails principal,
                                                          @RequestParam(required = false) Integer year) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        int y = (year != null) ? year : LocalDate.now().getYear();
        LocalDate start = LocalDate.of(y, 1, 1);
        LocalDate end = LocalDate.of(y, 12, 31);

        List<RainfallRecord> list = rainfallRepo.findAllByFarmerAndRecordDateBetweenOrderByRecordDateAsc(user, start, end);
        Map<String, Double> monthly = new LinkedHashMap<>();
        for (int m = 1; m <= 12; m++) {
            monthly.put(String.format("%02d", m), 0.0);
        }

        double total = 0.0;
        for (RainfallRecord r : list) {
            String m = String.format("%02d", r.getRecordDate().getMonthValue());
            monthly.put(m, Math.round((monthly.get(m) + r.getRainfallMm()) * 10.0) / 10.0);
            total += r.getRainfallMm();
        }

        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("year", y);
        resp.put("totalMm", Math.round(total * 10.0) / 10.0);
        resp.put("monthly", monthly);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/historical")
    public ResponseEntity<List<HistoricalRainfall>> getHistorical(@RequestParam(defaultValue = "MADHYA MAHARASHTRA") String subdivision) {
        return ResponseEntity.ok(historicalRepo.findAllBySubdivisionOrderByYearAsc(subdivision.toUpperCase()));
    }

    @GetMapping("/district-normal")
    public ResponseEntity<DistrictRainfallNormal> getDistrictNormal(@RequestParam(defaultValue = "Pune") String district) {
        return normalRepo.findByDistrictIgnoreCase(district)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private RainfallRecordDto toDto(RainfallRecord r) {
        return RainfallRecordDto.builder()
                .id(r.getId())
                .recordDate(r.getRecordDate())
                .rainfallMm(r.getRainfallMm())
                .district(r.getDistrict())
                .taluka(r.getTaluka())
                .note(r.getNote())
                .build();
    }

    public RainfallController(RainfallRecordRepository rainfallRepo, UserRepository userRepo, HistoricalRainfallRepository historicalRepo, DistrictRainfallNormalRepository normalRepo) {
        this.rainfallRepo = rainfallRepo;
        this.userRepo = userRepo;
        this.historicalRepo = historicalRepo;
        this.normalRepo = normalRepo;
    }

}
