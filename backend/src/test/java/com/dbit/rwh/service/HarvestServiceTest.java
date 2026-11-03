package com.dbit.rwh.service;

import com.dbit.rwh.dto.HarvestCalculateRequest;
import com.dbit.rwh.dto.HarvestCalculateResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HarvestServiceTest {

    private HarvestService harvestService;

    @BeforeEach
    void setUp() {
        harvestService = new HarvestService(null);
    }

    @Test
    @DisplayName("Should accurately calculate harvested water using standard formula: Area * Rain * Coeff")
    void testStandardCalculationFormula() {
        // Area: 100 m2, Rainfall: 50 mm, Surface: Roof/concrete (coeff = 0.85)
        // Expected: 100 * 50 * 0.85 = 4,250 Litres
        HarvestCalculateRequest request = new HarvestCalculateRequest();
        request.setCatchmentAreaSqm(100.0);
        request.setRainfallMm(50.0);
        request.setSurfaceType("Roof/concrete");

        HarvestCalculateResponse response = harvestService.calculate(request);

        assertNotNull(response);
        assertEquals(4250.0, response.getHarvestedLitres(), 0.01);
        assertEquals(4.25, response.getHarvestedCubicMetres(), 0.01);
        assertEquals(4.25, response.getThousandLitreTanksFilled(), 0.1);
        assertEquals(0.85, response.getRunoffCoefficient(), 0.001);
    }

    @Test
    @DisplayName("Should use farmland coefficient (0.40) by default or when specified")
    void testFarmlandCalculation() {
        // 1 Acre = 4046.86 m2, 700 mm rainfall, Farmland/cropped (0.40)
        HarvestCalculateRequest request = new HarvestCalculateRequest();
        request.setCatchmentAreaSqm(4046.86);
        request.setRainfallMm(700.0);
        request.setSurfaceType("Farmland/cropped");

        HarvestCalculateResponse response = harvestService.calculate(request);

        double expectedLitres = 4046.86 * 700.0 * 0.40;
        assertEquals(Math.round(expectedLitres * 100.0) / 100.0, response.getHarvestedLitres(), 0.1);
        assertEquals(0.40, response.getRunoffCoefficient(), 0.001);
    }

    @Test
    @DisplayName("Should return 0 litres when rainfall or area is zero")
    void testZeroRainfall() {
        HarvestCalculateRequest request = new HarvestCalculateRequest();
        request.setCatchmentAreaSqm(500.0);
        request.setRainfallMm(0.0);
        request.setSurfaceType("Roof/concrete");

        HarvestCalculateResponse response = harvestService.calculate(request);
        assertEquals(0.0, response.getHarvestedLitres());
    }

    @Test
    @DisplayName("Should correctly recognize all predefined runoff surface types")
    void testRunoffCoefficients() {
        assertEquals(0.85, HarvestService.getCoefficient("Roof/concrete"), 0.001);
        assertEquals(0.60, HarvestService.getCoefficient("Compacted soil"), 0.001);
        assertEquals(0.40, HarvestService.getCoefficient("Farmland/cropped"), 0.001);
        assertEquals(0.30, HarvestService.getCoefficient("Grassland"), 0.001);
        assertEquals(0.20, HarvestService.getCoefficient("Forest/vegetated"), 0.001);
        assertEquals(0.40, HarvestService.getCoefficient(null), 0.001);
    }
}
