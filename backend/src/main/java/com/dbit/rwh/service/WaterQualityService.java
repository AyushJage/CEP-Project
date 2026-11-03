package com.dbit.rwh.service;

import com.dbit.rwh.dto.WaterQualityTestDto;
import com.dbit.rwh.entity.Tank;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.entity.WaterQualityTest;
import com.dbit.rwh.repository.TankRepository;
import com.dbit.rwh.repository.WaterQualityTestRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WaterQualityService {

    private final WaterQualityTestRepository testRepo;
    private final TankRepository tankRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WaterQualityTestDto evaluate(User farmer, WaterQualityTestDto req) {
        List<String> advice = new ArrayList<>();
        boolean coliform = Boolean.TRUE.equals(req.getColiformPresent());

        String verdictDrinking;
        String verdictIrrigation;

        // Drinking assessment (BIS IS 10500)
        if (coliform) {
            verdictDrinking = "Not Safe (पिण्यासाठी अयोग्य)";
            advice.add("Microbial coliform contamination detected! Do not drink without boiling for at least 10 minutes or using chlorine treatment.");
        } else if ((req.getPh() != null && (req.getPh() < 6.5 || req.getPh() > 8.5)) ||
                   (req.getTdsOrConductivity() != null && req.getTdsOrConductivity() > 1000.0) ||
                   (req.getTurbidity() != null && req.getTurbidity() > 5.0) ||
                   (req.getNitrate() != null && req.getNitrate() > 45.0)) {
            verdictDrinking = "Caution (सावधगिरी)";
            advice.add("Parameters exceed ideal BIS 10500 drinking limits. Pass through a 3-stage slow sand-gravel-charcoal filter before consumption.");
        } else {
            verdictDrinking = "Safe (पिण्यासाठी योग्य)";
            advice.add("Water quality conforms to standard drinking thresholds. Maintain rooftop first-flush cleanliness.");
        }

        // Irrigation assessment
        if (req.getTdsOrConductivity() != null && req.getTdsOrConductivity() > 2000.0) {
            verdictIrrigation = "Caution (सिंचनासाठी सावधगिरी)";
            advice.add("High dissolved solids can lead to soil salinization. Mix with fresh rainwater or use only for salt-tolerant crops.");
        } else if (req.getPh() != null && (req.getPh() < 5.5 || req.getPh() > 8.8)) {
            verdictIrrigation = "Caution (मध्यम)";
            advice.add("pH is outside optimal crop absorption range (6.0 - 8.0).");
        } else {
            verdictIrrigation = "Safe (सिंचनासाठी सुरक्षित)";
            advice.add("Excellent water quality for micro-irrigation and drip emitters without clogging risks.");
        }

        advice.add("Note: This is an advisory assessment. For legal potable certification, verify water samples at your District Public Health Laboratory (भूजल सर्वेक्षण व विकास यंत्रणा - GSDA).");

        return WaterQualityTestDto.builder()
                .tankId(req.getTankId())
                .testDate(req.getTestDate() != null ? req.getTestDate() : LocalDate.now())
                .ph(req.getPh())
                .tdsOrConductivity(req.getTdsOrConductivity())
                .turbidity(req.getTurbidity())
                .nitrate(req.getNitrate())
                .coliformPresent(req.getColiformPresent())
                .verdictDrinking(verdictDrinking)
                .verdictIrrigation(verdictIrrigation)
                .advice(advice)
                .build();
    }

    @Transactional
    public WaterQualityTestDto evaluateAndSave(User farmer, WaterQualityTestDto req) {
        WaterQualityTestDto dto = evaluate(farmer, req);

        Tank tank = null;
        if (req.getTankId() != null) {
            tank = tankRepo.findById(req.getTankId()).orElse(null);
        }

        String adviceJson = "[]";
        try {
            adviceJson = objectMapper.writeValueAsString(dto.getAdvice());
        } catch (Exception ignored) {}

        WaterQualityTest entity = WaterQualityTest.builder()
                .farmer(farmer)
                .tank(tank)
                .testDate(dto.getTestDate())
                .ph(dto.getPh())
                .tdsOrConductivity(dto.getTdsOrConductivity())
                .turbidity(dto.getTurbidity())
                .nitrate(dto.getNitrate())
                .coliformPresent(dto.getColiformPresent())
                .verdictDrinking(dto.getVerdictDrinking())
                .verdictIrrigation(dto.getVerdictIrrigation())
                .adviceJson(adviceJson)
                .build();
        testRepo.save(entity);

        dto.setId(entity.getId());
        dto.setTankName(tank != null ? tank.getName() : "-");
        return dto;
    }

    public List<WaterQualityTestDto> getHistory(User farmer) {
        return testRepo.findAllByFarmerOrderByTestDateDesc(farmer).stream()
                .map(t -> {
                    List<String> adv = Collections.emptyList();
                    try {
                        if (t.getAdviceJson() != null) {
                            adv = objectMapper.readValue(t.getAdviceJson(), new TypeReference<List<String>>() {});
                        }
                    } catch (Exception ignored) {}

                    return WaterQualityTestDto.builder()
                            .id(t.getId())
                            .tankId(t.getTank() != null ? t.getTank().getId() : null)
                            .tankName(t.getTank() != null ? t.getTank().getName() : "-")
                            .testDate(t.getTestDate())
                            .ph(t.getPh())
                            .tdsOrConductivity(t.getTdsOrConductivity())
                            .turbidity(t.getTurbidity())
                            .nitrate(t.getNitrate())
                            .coliformPresent(t.getColiformPresent())
                            .verdictDrinking(t.getVerdictDrinking())
                            .verdictIrrigation(t.getVerdictIrrigation())
                            .advice(adv)
                            .build();
                })
                .toList();
    }

    public WaterQualityService(WaterQualityTestRepository testRepo, TankRepository tankRepo) {
        this.testRepo = testRepo;
        this.tankRepo = tankRepo;
    }

}
