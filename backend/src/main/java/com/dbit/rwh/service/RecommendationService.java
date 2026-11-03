package com.dbit.rwh.service;

import com.dbit.rwh.dto.RecommendationDto;
import com.dbit.rwh.entity.Recommendation;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.RecommendationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationService {

    private final RecommendationRepository recommendationRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RecommendationDto generateRecommendation(Double landAreaSqm,
                                                     String irrigationSource,
                                                     String existingStructure,
                                                     String soilType,
                                                     String district) {

        double landAcres = (landAreaSqm != null) ? (landAreaSqm / 4046.86) : 2.0;
        boolean hasStructure = existingStructure != null && !existingStructure.equalsIgnoreCase("None");
        String irrigation = (irrigationSource != null) ? irrigationSource : "Rain-fed";

        String structureType;
        double capacityLitres;
        String reasonSummary;
        String lowCostOption;
        String mechanisedOption;
        List<String> tips = new ArrayList<>();

        if (!hasStructure && irrigation.equalsIgnoreCase("Rain-fed")) {
            if (landAcres <= 2.0) {
                structureType = "Jalkund / Recharge Pit (जलकुंड / पुनर्भरण खड्डा)";
                capacityLitres = 20000.0;
                reasonSummary = "For small rain-fed farms (< 2 acres), a low-cost lined Jalkund (6,000–20,000 L) protects critical kharif-to-rabi dry spells with minimum capital.";
                lowCostOption = "Manual excavation of 5m x 4m x 1m pit lined with UV-stabilized 300-500 micron silpaulin sheet (Approx cost: ₹4,000 - ₹8,000).";
                mechanisedOption = "Pre-cast ferrocement tank or brick masonry storage unit (Approx cost: ₹25,000 - ₹40,000).";
            } else {
                structureType = "Farm Pond / Shet-tale (शेततळे)";
                capacityLitres = 100000.0;
                reasonSummary = "For medium to large rain-fed land (> 2 acres), an unlined/lined farm pond (30m x 30m x 3m) harvests abundant monsoon surface runoff.";
                lowCostOption = "Community shramdaan or tractor-blade pit excavation without synthetic liner in heavy black cotton soil (Approx cost: ₹15,000 - ₹30,000).";
                mechanisedOption = "JCB-excavated 30x30m farm pond with 500-micron geotextile geomembrane lining and solar pump (Approx cost: ₹80,000 - ₹1,50,000; eligible for Magel Tyala Shettale subsidy).";
            }
        } else if (irrigation.equalsIgnoreCase("Borewell") || irrigation.equalsIgnoreCase("Well")) {
            structureType = "Direct Well Recharge Pit & Filter (विहीर / बोअरवेल पुनर्भरण रचना)";
            capacityLitres = 50000.0;
            reasonSummary = "You already rely on groundwater. Channelling filtered surface runoff directly into the aquifer reverses water table depletion.";
            lowCostOption = "Desilt inlet trench, build a 3-layer sand/gravel/charcoal recharge pit around the well casing (Approx cost: ₹3,000 - ₹6,000).";
            mechanisedOption = "Engineered shaft injection system with dual-chamber automated first-flush separator (Approx cost: ₹20,000 - ₹35,000).";
        } else {
            structureType = "Contour Trenches & Rooftop Harvesting (सलग समपातळी चर आणि छतावरील पाणी साठवण)";
            capacityLitres = 15000.0;
            reasonSummary = "Complement existing irrigation with rooftop rainwater capture for drinking/domestic use and contour bunding to arrest field runoff.";
            lowCostOption = "PVC gutters from rooftop with manual first-flush valve diverted to plastic syntax tank or soak pit (Approx cost: ₹5,000 - ₹10,000).";
            mechanisedOption = "Automated rooftop multi-cyclone separator linked to underground masonry cistern (Approx cost: ₹30,000 - ₹50,000).";
        }

        // Prioritised conservation tips
        tips.add("Organic Mulching (आच्छादन): Cover inter-rows with crop residue (tur/soybean straw) to reduce soil moisture evaporation by up to 40%.");
        tips.add("Drip Irrigation (ठिबक सिंचन): Saves 50-60% water compared to flood furrow irrigation; apply for state subsidy under PM Krishi Sinchayee Yojana.");
        tips.add("Sowing Timing (पेरणीची वेळ): Sow kharif crops only after your taluka receives at least 75-100 mm of sustained cumulative monsoon rainfall.");
        tips.add("Farm Perimeter Bunding (बांधबंदिस्ती): Maintain 30-45 cm earthen field bunds to hold rainwater directly in the crop root zone.");
        tips.add("Desilting (गाळ काढणे): Periodically remove silt from tank beds before June monsoon to restore 100% designed storage capacity.");

        return RecommendationDto.builder()
                .structureType(structureType)
                .suggestedCapacityLitres(capacityLitres)
                .reasonSummary(reasonSummary)
                .lowCostOption(lowCostOption)
                .mechanisedOption(mechanisedOption)
                .prioritisedTips(tips)
                .build();
    }

    @Transactional
    public RecommendationDto generateAndSave(User farmer,
                                             Double landAreaSqm,
                                             String irrigationSource,
                                             String existingStructure,
                                             String soilType,
                                             String district) {

        RecommendationDto dto = generateRecommendation(landAreaSqm, irrigationSource, existingStructure, soilType, district);

        String tipsJson = "[]";
        try {
            tipsJson = objectMapper.writeValueAsString(dto.getPrioritisedTips());
        } catch (Exception ignored) {}

        Recommendation entity = Recommendation.builder()
                .farmer(farmer)
                .structureType(dto.getStructureType())
                .suggestedCapacityLitres(dto.getSuggestedCapacityLitres())
                .reasonSummary(dto.getReasonSummary())
                .tipsJson(tipsJson)
                .build();
        recommendationRepo.save(entity);

        return dto;
    }

    public RecommendationService(RecommendationRepository recommendationRepo) {
        this.recommendationRepo = recommendationRepo;
    }

}
