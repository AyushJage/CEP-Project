package com.dbit.rwh.service;

import com.dbit.rwh.dto.FertilityAssessRequest;
import com.dbit.rwh.dto.FertilityReportDto;
import com.dbit.rwh.entity.FertilityReport;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.FertilityReportRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class FertilityService {

    private final FertilityReportRepository fertilityRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FertilityReportDto assess(FertilityAssessRequest req) {
        Map<String, String> statusMap = new LinkedHashMap<>();
        List<String> tips = new ArrayList<>();

        double phScore = 75.0;
        if (req.getPh() != null) {
            double ph = req.getPh();
            if (ph >= 6.5 && ph <= 7.5) {
                phScore = 100.0;
                statusMap.put("pH", "Ideal");
            } else if ((ph >= 6.0 && ph < 6.5) || (ph > 7.5 && ph <= 8.0)) {
                phScore = 75.0;
                statusMap.put("pH", "Moderate");
                if (ph < 6.5) tips.add("Apply agricultural lime to balance acidic soil / आम्लधर्मीय जमिनीसाठी चुना वापरा.");
                else tips.add("Apply gypsum or organic compost to lower slight alkalinity / जिप्सम व शेणखताचा वापर करा.");
            } else if (ph < 6.0) {
                phScore = 40.0;
                statusMap.put("pH", "Acidic");
                tips.add("High acidity detected. Apply 2-3 tons of lime or dolomite per hectare / जमिनीचा सामू वाढवण्यासाठी कृषी चुना किंवा डोलोमाइट वापरा.");
            } else {
                phScore = 40.0;
                statusMap.put("pH", "Alkaline");
                tips.add("High alkalinity detected. Use gypsum and green manuring / क्षारयुक्त जमिनीसाठी जिप्सम आणि हिरवळीची खते वापरा.");
            }
        } else {
            statusMap.put("pH", "Estimated Normal");
        }

        double nScore = 60.0;
        if (req.getNitrogen() != null) {
            double n = req.getNitrogen();
            if (n < 280) {
                nScore = 35.0;
                statusMap.put("Nitrogen", "Low (< 280 kg/ha)");
                tips.add("Low nitrogen: Add well-decomposed farmyard manure, neem cake, and biofertilizers / शेणखत व अझोटोबॅक्टर जीवाणू खत वापरा.");
            } else if (n <= 560) {
                nScore = 80.0;
                statusMap.put("Nitrogen", "Medium (280-560 kg/ha)");
            } else {
                nScore = 100.0;
                statusMap.put("Nitrogen", "High (> 560 kg/ha)");
            }
        }

        double pScore = 60.0;
        if (req.getPhosphorus() != null) {
            double p = req.getPhosphorus();
            if (p < 11) {
                pScore = 35.0;
                statusMap.put("Phosphorus", "Low (< 11 kg/ha)");
                tips.add("Low phosphorus: Inoculate soil with PSB and apply single super phosphate / पीएसबी (PSB) जीवाणू संवर्धन वापरा.");
            } else if (p <= 25) {
                pScore = 80.0;
                statusMap.put("Phosphorus", "Medium (11-25 kg/ha)");
            } else {
                pScore = 100.0;
                statusMap.put("Phosphorus", "High (> 25 kg/ha)");
            }
        }

        double kScore = 60.0;
        if (req.getPotassium() != null) {
            double k = req.getPotassium();
            if (k < 110) {
                kScore = 35.0;
                statusMap.put("Potassium", "Low (< 110 kg/ha)");
                tips.add("Low potassium: Add wood ash or Muriate of Potash to enhance drought resistance / पालाश वाढवण्यासाठी पोटॅश खतांचा वापर करा.");
            } else if (k <= 280) {
                kScore = 80.0;
                statusMap.put("Potassium", "Medium (110-280 kg/ha)");
            } else {
                kScore = 100.0;
                statusMap.put("Potassium", "High (> 280 kg/ha)");
            }
        }

        double ocScore = 60.0;
        if (req.getOrganicCarbon() != null) {
            double oc = req.getOrganicCarbon();
            if (oc < 0.5) {
                ocScore = 30.0;
                statusMap.put("Organic Carbon", "Low (< 0.5%)");
                tips.add("Critical: Soil carbon is low. Adopt mulching and vermicompost / सेंद्रिय कर्ब वाढवण्यासाठी गांडूळखत व आच्छादन करा.");
            } else if (oc <= 0.75) {
                ocScore = 75.0;
                statusMap.put("Organic Carbon", "Medium (0.5-0.75%)");
            } else {
                ocScore = 100.0;
                statusMap.put("Organic Carbon", "Good (> 0.75%)");
            }
        }

        double ecScore = 80.0;
        if (req.getEc() != null) {
            double ec = req.getEc();
            if (ec < 1.0) {
                ecScore = 100.0;
                statusMap.put("EC", "Normal (< 1.0 dS/m)");
            } else if (ec <= 2.0) {
                ecScore = 50.0;
                statusMap.put("EC", "Critical (1.0-2.0 dS/m)");
                tips.add("Salinity detected: Ensure good sub-surface drainage to flush salts away / जमिनीत पाणी साचू न देता पाण्याचा निचरा करा.");
            } else {
                ecScore = 20.0;
                statusMap.put("EC", "Injurious (> 2.0 dS/m)");
                tips.add("Severe salinity: Install drainage trenches and use salt-tolerant crops / क्षार सहन करणारे पिके निवडा.");
            }
        }

        // Weighted total fertility score (0 - 100)
        double totalScore = (phScore * 0.20) + (nScore * 0.20) + (pScore * 0.15) + (kScore * 0.15) + (ocScore * 0.20) + (ecScore * 0.10);
        totalScore = Math.round(totalScore * 10.0) / 10.0;

        String band;
        if (totalScore >= 80) band = "Excellent";
        else if (totalScore >= 60) band = "Good";
        else if (totalScore >= 40) band = "Moderate";
        else band = "Poor";

        if (tips.isEmpty()) {
            tips.add("Maintain current organic practices with regular compost and crop rotation / नियमित सेंद्रिय खत व फेरपालट चालू ठेवा.");
        }

        return FertilityReportDto.builder()
                .ph(req.getPh())
                .nitrogen(req.getNitrogen())
                .phosphorus(req.getPhosphorus())
                .potassium(req.getPotassium())
                .organicCarbon(req.getOrganicCarbon())
                .ec(req.getEc())
                .score(totalScore)
                .band(band)
                .parameterStatus(statusMap)
                .tips(tips)
                .build();
    }

    @Transactional
    public FertilityReportDto assessAndSave(User farmer, FertilityAssessRequest req) {
        FertilityReportDto dto = assess(req);
        String tipsJson = "[]";
        try {
            tipsJson = objectMapper.writeValueAsString(dto.getTips());
        } catch (Exception ignored) {}

        FertilityReport report = FertilityReport.builder()
                .farmer(farmer)
                .ph(req.getPh())
                .nitrogen(req.getNitrogen())
                .phosphorus(req.getPhosphorus())
                .potassium(req.getPotassium())
                .organicCarbon(req.getOrganicCarbon())
                .ec(req.getEc())
                .score(dto.getScore())
                .band(dto.getBand())
                .tipsJson(tipsJson)
                .build();
        fertilityRepo.save(report);

        dto.setId(report.getId());
        dto.setCreatedAt(report.getCreatedAt());
        return dto;
    }

    public List<FertilityReportDto> getFarmerHistory(User farmer) {
        return fertilityRepo.findAllByFarmerOrderByCreatedAtDesc(farmer)
                .stream()
                .map(r -> {
                    List<String> tips = Collections.emptyList();
                    try {
                        if (r.getTipsJson() != null) {
                            tips = objectMapper.readValue(r.getTipsJson(), new TypeReference<List<String>>() {});
                        }
                    } catch (Exception ignored) {}

                    return FertilityReportDto.builder()
                            .id(r.getId())
                            .ph(r.getPh())
                            .nitrogen(r.getNitrogen())
                            .phosphorus(r.getPhosphorus())
                            .potassium(r.getPotassium())
                            .organicCarbon(r.getOrganicCarbon())
                            .ec(r.getEc())
                            .score(r.getScore())
                            .band(r.getBand())
                            .tips(tips)
                            .createdAt(r.getCreatedAt())
                            .build();
                })
                .toList();
    }

    public FertilityService(FertilityReportRepository fertilityRepo) {
        this.fertilityRepo = fertilityRepo;
    }

}
