package com.dbit.rwh.service;

import com.dbit.rwh.dto.HarvestCalculateRequest;
import com.dbit.rwh.dto.HarvestCalculateResponse;
import com.dbit.rwh.entity.HarvestCalculation;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.HarvestCalculationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HarvestService {

    private final HarvestCalculationRepository harvestRepo;

    public static final Map<String, Double> RUNOFF_COEFFICIENTS = new HashMap<>();

    static {
        RUNOFF_COEFFICIENTS.put("Roof/concrete", 0.85);
        RUNOFF_COEFFICIENTS.put("Compacted soil", 0.60);
        RUNOFF_COEFFICIENTS.put("Farmland/cropped", 0.40);
        RUNOFF_COEFFICIENTS.put("Grassland", 0.30);
        RUNOFF_COEFFICIENTS.put("Forest/vegetated", 0.20);
    }

    public static double getCoefficient(String surfaceType) {
        if (surfaceType == null) return 0.40;
        for (Map.Entry<String, Double> entry : RUNOFF_COEFFICIENTS.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(surfaceType.trim())) {
                return entry.getValue();
            }
        }
        return 0.40; // default farmland
    }

    public HarvestCalculateResponse calculate(HarvestCalculateRequest request) {
        double coefficient = getCoefficient(request.getSurfaceType());
        // Formula: Litres = Area (m2) * Rainfall (mm) * Coefficient
        double litres = request.getCatchmentAreaSqm() * request.getRainfallMm() * coefficient;
        double cubicMetres = litres / 1000.0;
        double tanksFilled = litres / 1000.0;

        return HarvestCalculateResponse.builder()
                .catchmentAreaSqm(request.getCatchmentAreaSqm())
                .rainfallMm(request.getRainfallMm())
                .runoffCoefficient(coefficient)
                .surfaceType(request.getSurfaceType())
                .harvestedLitres(Math.round(litres * 100.0) / 100.0)
                .harvestedCubicMetres(Math.round(cubicMetres * 100.0) / 100.0)
                .thousandLitreTanksFilled(Math.round(tanksFilled * 10.0) / 10.0)
                .build();
    }

    @Transactional
    public HarvestCalculateResponse calculateAndSave(User farmer, HarvestCalculateRequest request) {
        HarvestCalculateResponse response = calculate(request);

        HarvestCalculation entity = HarvestCalculation.builder()
                .farmer(farmer)
                .catchmentAreaSqm(response.getCatchmentAreaSqm())
                .rainfallMm(response.getRainfallMm())
                .runoffCoefficient(response.getRunoffCoefficient())
                .surfaceType(response.getSurfaceType())
                .harvestedLitres(response.getHarvestedLitres())
                .build();
        harvestRepo.save(entity);

        return response;
    }

    public List<HarvestCalculateResponse> getFarmerHistory(User farmer) {
        return harvestRepo.findAllByFarmerOrderByCreatedAtDesc(farmer)
                .stream()
                .map(h -> HarvestCalculateResponse.builder()
                        .catchmentAreaSqm(h.getCatchmentAreaSqm())
                        .rainfallMm(h.getRainfallMm())
                        .runoffCoefficient(h.getRunoffCoefficient())
                        .surfaceType(h.getSurfaceType())
                        .harvestedLitres(h.getHarvestedLitres())
                        .harvestedCubicMetres(Math.round((h.getHarvestedLitres() / 1000.0) * 100.0) / 100.0)
                        .thousandLitreTanksFilled(Math.round((h.getHarvestedLitres() / 1000.0) * 10.0) / 10.0)
                        .build())
                .collect(Collectors.toList());
    }

    public HarvestService(HarvestCalculationRepository harvestRepo) {
        this.harvestRepo = harvestRepo;
    }

}
