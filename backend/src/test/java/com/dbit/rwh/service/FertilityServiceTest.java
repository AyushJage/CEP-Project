package com.dbit.rwh.service;

import com.dbit.rwh.dto.FertilityAssessRequest;
import com.dbit.rwh.dto.FertilityReportDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FertilityServiceTest {

    private FertilityService fertilityService;

    @BeforeEach
    void setUp() {
        fertilityService = new FertilityService(null);
    }

    @Test
    @DisplayName("Should score ideal soil parameters as Good or Excellent band")
    void testIdealSoilParameters() {
        FertilityAssessRequest req = new FertilityAssessRequest();
        req.setPh(7.0);          // Ideal (6.5 - 7.5)
        req.setNitrogen(420.0);   // Medium (280 - 560 kg/ha)
        req.setPhosphorus(22.0);  // Medium (11 - 25 kg/ha)
        req.setPotassium(220.0);  // Medium (110 - 280 kg/ha)
        req.setOrganicCarbon(0.85); // High (> 0.75%)

        FertilityReportDto dto = fertilityService.assess(req);

        assertNotNull(dto);
        assertTrue(dto.getScore() >= 70.0, "Ideal soil should have high score");
        assertTrue(dto.getBand().contains("Good") || dto.getBand().contains("Excellent") || dto.getBand().contains("उत्कृष्ट") || dto.getBand().contains("चांगली"));
        assertTrue(dto.getParameterStatus().get("pH").contains("Ideal"));
    }

    @Test
    @DisplayName("Should detect acidic soil and suggest agricultural lime")
    void testAcidicSoil() {
        FertilityAssessRequest req = new FertilityAssessRequest();
        req.setPh(5.4); // Acidic
        req.setNitrogen(200.0);

        FertilityReportDto dto = fertilityService.assess(req);

        assertNotNull(dto);
        assertTrue(dto.getParameterStatus().get("pH").contains("Acidic"));
        boolean hasLimeTip = dto.getTips().stream().anyMatch(t -> t.toLowerCase().contains("lime") || t.contains("चुना"));
        assertTrue(hasLimeTip, "Tips should suggest lime for acidic soil");
    }

    @Test
    @DisplayName("Should score degraded depleted soil as Poor band")
    void testPoorSoil() {
        FertilityAssessRequest req = new FertilityAssessRequest();
        req.setPh(8.8); // Highly Alkaline
        req.setNitrogen(120.0); // Very low N
        req.setPhosphorus(6.0); // Very low P
        req.setPotassium(60.0); // Very low K
        req.setOrganicCarbon(0.25); // Very low OC

        FertilityReportDto dto = fertilityService.assess(req);

        assertNotNull(dto);
        assertTrue(dto.getScore() < 50.0, "Depleted soil should score below 50");
        assertTrue(dto.getBand().contains("Poor") || dto.getBand().contains("कमी"));
    }
}
