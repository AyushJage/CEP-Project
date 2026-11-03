package com.dbit.rwh.dto;

import java.util.Map;

public class AdminSummaryDto {
    private Long totalFarmers;
    private Double totalHarvestedLitres;
    private Double adoptionRatePercent;
    private Long totalTanks;
    private Double avgFertilityScore;
    private Map<String, Long> talukaFarmerCounts;
    private Map<String, Double> talukaAdoptionRates;

    public AdminSummaryDto() {}

    public AdminSummaryDto(Long totalFarmers, Double totalHarvestedLitres, Double adoptionRatePercent, Long totalTanks, Double avgFertilityScore, Map<String, Long> talukaFarmerCounts, Map<String, Double> talukaAdoptionRates) {
        this.totalFarmers = totalFarmers;
        this.totalHarvestedLitres = totalHarvestedLitres;
        this.adoptionRatePercent = adoptionRatePercent;
        this.totalTanks = totalTanks;
        this.avgFertilityScore = avgFertilityScore;
        this.talukaFarmerCounts = talukaFarmerCounts;
        this.talukaAdoptionRates = talukaAdoptionRates;
    }

    public Long getTotalFarmers() {
        return this.totalFarmers;
    }

    public void setTotalFarmers(Long totalFarmers) {
        this.totalFarmers = totalFarmers;
    }

    public Double getTotalHarvestedLitres() {
        return this.totalHarvestedLitres;
    }

    public void setTotalHarvestedLitres(Double totalHarvestedLitres) {
        this.totalHarvestedLitres = totalHarvestedLitres;
    }

    public Double getAdoptionRatePercent() {
        return this.adoptionRatePercent;
    }

    public void setAdoptionRatePercent(Double adoptionRatePercent) {
        this.adoptionRatePercent = adoptionRatePercent;
    }

    public Long getTotalTanks() {
        return this.totalTanks;
    }

    public void setTotalTanks(Long totalTanks) {
        this.totalTanks = totalTanks;
    }

    public Double getAvgFertilityScore() {
        return this.avgFertilityScore;
    }

    public void setAvgFertilityScore(Double avgFertilityScore) {
        this.avgFertilityScore = avgFertilityScore;
    }

    public Map<String, Long> getTalukaFarmerCounts() {
        return this.talukaFarmerCounts;
    }

    public void setTalukaFarmerCounts(Map<String, Long> talukaFarmerCounts) {
        this.talukaFarmerCounts = talukaFarmerCounts;
    }

    public Map<String, Double> getTalukaAdoptionRates() {
        return this.talukaAdoptionRates;
    }

    public void setTalukaAdoptionRates(Map<String, Double> talukaAdoptionRates) {
        this.talukaAdoptionRates = talukaAdoptionRates;
    }

    public static AdminSummaryDtoBuilder builder() {
        return new AdminSummaryDtoBuilder();
    }

    public static class AdminSummaryDtoBuilder {
        private Long totalFarmers;
        private Double totalHarvestedLitres;
        private Double adoptionRatePercent;
        private Long totalTanks;
        private Double avgFertilityScore;
        private Map<String, Long> talukaFarmerCounts;
        private Map<String, Double> talukaAdoptionRates;

        public AdminSummaryDtoBuilder totalFarmers(Long totalFarmers) {
            this.totalFarmers = totalFarmers;
            return this;
        }

        public AdminSummaryDtoBuilder totalHarvestedLitres(Double totalHarvestedLitres) {
            this.totalHarvestedLitres = totalHarvestedLitres;
            return this;
        }

        public AdminSummaryDtoBuilder adoptionRatePercent(Double adoptionRatePercent) {
            this.adoptionRatePercent = adoptionRatePercent;
            return this;
        }

        public AdminSummaryDtoBuilder totalTanks(Long totalTanks) {
            this.totalTanks = totalTanks;
            return this;
        }

        public AdminSummaryDtoBuilder avgFertilityScore(Double avgFertilityScore) {
            this.avgFertilityScore = avgFertilityScore;
            return this;
        }

        public AdminSummaryDtoBuilder talukaFarmerCounts(Map<String, Long> talukaFarmerCounts) {
            this.talukaFarmerCounts = talukaFarmerCounts;
            return this;
        }

        public AdminSummaryDtoBuilder talukaAdoptionRates(Map<String, Double> talukaAdoptionRates) {
            this.talukaAdoptionRates = talukaAdoptionRates;
            return this;
        }

        public AdminSummaryDto build() {
            AdminSummaryDto instance = new AdminSummaryDto();
            instance.setTotalFarmers(this.totalFarmers);
            instance.setTotalHarvestedLitres(this.totalHarvestedLitres);
            instance.setAdoptionRatePercent(this.adoptionRatePercent);
            instance.setTotalTanks(this.totalTanks);
            instance.setAvgFertilityScore(this.avgFertilityScore);
            instance.setTalukaFarmerCounts(this.talukaFarmerCounts);
            instance.setTalukaAdoptionRates(this.talukaAdoptionRates);
            return instance;
        }
    }

}
