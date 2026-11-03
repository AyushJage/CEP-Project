package com.dbit.rwh.service;

import com.dbit.rwh.dto.RecommendationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecommendationServiceTest {

    private RecommendationService recommendationService;

    @BeforeEach
    void setUp() {
        recommendationService = new RecommendationService(null);
    }

    @Test
    @DisplayName("Should recommend Jalkund for small rain-fed farms (< 2 acres) with no structure")
    void testSmallRainfedFarmRecommendation() {
        // 1 Acre = 4046.86 m2 (< 2 acres), Rain-fed, No structure
        RecommendationDto dto = recommendationService.generateRecommendation(
                4046.86,
                "Rain-fed",
                "None",
                "Black Cotton",
                "Solapur"
        );

        assertNotNull(dto);
        assertTrue(dto.getStructureType().contains("Jalkund") || dto.getStructureType().contains("जलकुंड"));
        assertEquals(20000.0, dto.getSuggestedCapacityLitres());
        assertNotNull(dto.getLowCostOption());
        assertNotNull(dto.getMechanisedOption());
        assertFalse(dto.getPrioritisedTips().isEmpty());
    }

    @Test
    @DisplayName("Should recommend Farm Pond for large rain-fed farms (> 2 acres) with no structure")
    void testLargeRainfedFarmRecommendation() {
        // 4 Acres = 16187.44 m2, Rain-fed, No structure
        RecommendationDto dto = recommendationService.generateRecommendation(
                16187.44,
                "Rain-fed",
                "None",
                "Black Cotton",
                "Amravati"
        );

        assertNotNull(dto);
        assertTrue(dto.getStructureType().contains("Farm Pond") || dto.getStructureType().contains("शेततळे"));
        assertEquals(100000.0, dto.getSuggestedCapacityLitres());
        assertTrue(dto.getLowCostOption().toLowerCase().contains("shramdaan") || dto.getLowCostOption().contains("श्रमदान"));
    }

    @Test
    @DisplayName("Should recommend Direct Well Recharge when irrigation is Borewell or Open Well")
    void testBorewellRechargeRecommendation() {
        RecommendationDto dto = recommendationService.generateRecommendation(
                8000.0,
                "Borewell",
                "None",
                "Black Cotton",
                "Chhatrapati Sambhajinagar"
        );

        assertNotNull(dto);
        assertTrue(dto.getStructureType().contains("Recharge") || dto.getStructureType().contains("पुनर्भरण"));
        assertTrue(dto.getLowCostOption().contains("sand/gravel/charcoal") || dto.getLowCostOption().contains("फिल्टर"));
    }
}
