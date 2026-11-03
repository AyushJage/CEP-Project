package com.dbit.rwh.controller;

import com.dbit.rwh.entity.DistrictRainfallNormal;
import com.dbit.rwh.repository.DistrictRainfallNormalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/meta")
public class MetaController {

    private final DistrictRainfallNormalRepository normalRepo;

    @GetMapping("/options")
    public ResponseEntity<Map<String, Object>> getOptions() {
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("districts", List.of(
            "Ahmednagar", "Akola", "Amravati", "Beed", "Bhandara", "Buldhana", "Chandrapur",
            "Chhatrapati Sambhajinagar", "Dharashiv", "Dhule", "Gadchiroli", "Gondia", "Hingoli",
            "Jalgaon", "Jalna", "Kolhapur", "Latur", "Mumbai City", "Mumbai Suburban", "Nagpur",
            "Nanded", "Nandurbar", "Nashik", "Palghar", "Parbhani", "Pune", "Raigad", "Ratnagiri",
            "Sangli", "Satara", "Sindhudurg", "Solapur", "Thane", "Wardha", "Washim", "Yavatmal"
        ));

        map.put("crops", List.of(
            "Soybean", "Cotton", "Sugarcane", "Jowar", "Bajra", "Tur (Pigeon Pea)",
            "Moong", "Udid", "Wheat", "Gram (Harbara)", "Onion", "Grapes",
            "Pomegranate", "Banana", "Orange", "Turmeric", "Rice", "Groundnut", "Maize", "Vegetables"
        ));

        map.put("soilTypes", List.of(
            "Black (Regur) / काळी कसदार", "Red soil / तांबडी माती", "Laterite / जांभी माती",
            "Alluvial / गाळाची माती", "Loamy / पोयट्याची माती", "Sandy loam / रेतीयुक्त"
        ));

        map.put("irrigationSources", List.of(
            "Rain-fed (कोरडवाहू / केवळ पावसावर)", "Borewell (बोअरवेल)", "Open Well (विहीर)",
            "Canal (कालवा)", "River / Stream (नदी / ओढा)", "Other (इतर)"
        ));

        map.put("rwhStructures", List.of(
            "None (कोणतीही नाही)", "Farm pond (शेततळे)", "Storage tank (साठवण टाकी)",
            "Check dam (सिमेंट नाला बांध)", "Roof harvesting (छतावरील पाणी साठवण)", "Recharge pit (पुनर्भरण खड्डा)", "Other"
        ));

        map.put("surfaceTypes", List.of(
            Map.of("name", "Roof/concrete", "coeff", 0.85, "label", "Rooftop / Concrete (छत किंवा सिमेंट) - 0.85"),
            Map.of("name", "Compacted soil", "coeff", 0.60, "label", "Compacted soil / अंगण किंवा मातीचा रस्ता - 0.60"),
            Map.of("name", "Farmland/cropped", "coeff", 0.40, "label", "Farmland / पीक शेतजमीन - 0.40"),
            Map.of("name", "Grassland", "coeff", 0.30, "label", "Grassland / कुरण - 0.30"),
            Map.of("name", "Forest/vegetated", "coeff", 0.20, "label", "Vegetated / झाडांची जमीन - 0.20")
        ));

        map.put("areaUnits", List.of(
            Map.of("unit", "sqm", "factorToSqm", 1.0, "label", "Square Metres (चौ. मी.)"),
            Map.of("unit", "guntha", "factorToSqm", 101.17, "label", "Guntha (गुंठा) [१ एकर = ४० गुंठे]"),
            Map.of("unit", "acre", "factorToSqm", 4046.86, "label", "Acre (एकर) [४०४६.८६ चौ. मी.]"),
            Map.of("unit", "hectare", "factorToSqm", 10000.0, "label", "Hectare (हेक्टर) [१०,००० चौ. मी.]")
        ));

        return ResponseEntity.ok(map);
    }

    @GetMapping("/district-normals")
    public ResponseEntity<List<DistrictRainfallNormal>> getDistrictNormals() {
        return ResponseEntity.ok(normalRepo.findAll());
    }

    public MetaController(DistrictRainfallNormalRepository normalRepo) {
        this.normalRepo = normalRepo;
    }

}
