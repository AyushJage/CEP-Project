package com.dbit.rwh.dto;

import java.util.List;

public class WaterPlanResponse {

    public static class MonthBudget {
        private String monthName;
        private Double expectedRainfallMm;
        private Double expectedHarvestLitres;
        private Double plannedCropDemandLitres;
        private Double projectedStorageLitres;
        private String status;

        public MonthBudget() {}

        public MonthBudget(String monthName, Double expectedRainfallMm, Double expectedHarvestLitres,
                           Double plannedCropDemandLitres, Double projectedStorageLitres, String status) {
            this.monthName = monthName;
            this.expectedRainfallMm = expectedRainfallMm;
            this.expectedHarvestLitres = expectedHarvestLitres;
            this.plannedCropDemandLitres = plannedCropDemandLitres;
            this.projectedStorageLitres = projectedStorageLitres;
            this.status = status;
        }

        public String getMonthName() { return monthName; }
        public void setMonthName(String monthName) { this.monthName = monthName; }

        public Double getExpectedRainfallMm() { return expectedRainfallMm; }
        public void setExpectedRainfallMm(Double expectedRainfallMm) { this.expectedRainfallMm = expectedRainfallMm; }

        public Double getExpectedHarvestLitres() { return expectedHarvestLitres; }
        public void setExpectedHarvestLitres(Double expectedHarvestLitres) { this.expectedHarvestLitres = expectedHarvestLitres; }

        public Double getPlannedCropDemandLitres() { return plannedCropDemandLitres; }
        public void setPlannedCropDemandLitres(Double plannedCropDemandLitres) { this.plannedCropDemandLitres = plannedCropDemandLitres; }

        public Double getProjectedStorageLitres() { return projectedStorageLitres; }
        public void setProjectedStorageLitres(Double projectedStorageLitres) { this.projectedStorageLitres = projectedStorageLitres; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public static MonthBudgetBuilder builder() { return new MonthBudgetBuilder(); }

        public static class MonthBudgetBuilder {
            private String monthName;
            private Double expectedRainfallMm;
            private Double expectedHarvestLitres;
            private Double plannedCropDemandLitres;
            private Double projectedStorageLitres;
            private String status;

            public MonthBudgetBuilder monthName(String monthName) { this.monthName = monthName; return this; }
            public MonthBudgetBuilder expectedRainfallMm(Double expectedRainfallMm) { this.expectedRainfallMm = expectedRainfallMm; return this; }
            public MonthBudgetBuilder expectedHarvestLitres(Double expectedHarvestLitres) { this.expectedHarvestLitres = expectedHarvestLitres; return this; }
            public MonthBudgetBuilder plannedCropDemandLitres(Double plannedCropDemandLitres) { this.plannedCropDemandLitres = plannedCropDemandLitres; return this; }
            public MonthBudgetBuilder projectedStorageLitres(Double projectedStorageLitres) { this.projectedStorageLitres = projectedStorageLitres; return this; }
            public MonthBudgetBuilder status(String status) { this.status = status; return this; }

            public MonthBudget build() {
                return new MonthBudget(monthName, expectedRainfallMm, expectedHarvestLitres, plannedCropDemandLitres, projectedStorageLitres, status);
            }
        }
    }

    private String district;
    private String cropName;
    private String season;
    private List<MonthBudget> monthlyPlan;
    private Double totalHarvestLitres;
    private Double totalDemandLitres;
    private String verdict;
    private List<String> practicalTips;

    public WaterPlanResponse() {}

    public WaterPlanResponse(String district, String cropName, String season, List<MonthBudget> monthlyPlan,
                             Double totalHarvestLitres, Double totalDemandLitres, String verdict, List<String> practicalTips) {
        this.district = district;
        this.cropName = cropName;
        this.season = season;
        this.monthlyPlan = monthlyPlan;
        this.totalHarvestLitres = totalHarvestLitres;
        this.totalDemandLitres = totalDemandLitres;
        this.verdict = verdict;
        this.practicalTips = practicalTips;
    }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getCropName() { return cropName; }
    public void setCropName(String cropName) { this.cropName = cropName; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public List<MonthBudget> getMonthlyPlan() { return monthlyPlan; }
    public void setMonthlyPlan(List<MonthBudget> monthlyPlan) { this.monthlyPlan = monthlyPlan; }

    public Double getTotalHarvestLitres() { return totalHarvestLitres; }
    public void setTotalHarvestLitres(Double totalHarvestLitres) { this.totalHarvestLitres = totalHarvestLitres; }

    public Double getTotalDemandLitres() { return totalDemandLitres; }
    public void setTotalDemandLitres(Double totalDemandLitres) { this.totalDemandLitres = totalDemandLitres; }

    public String getVerdict() { return verdict; }
    public void setVerdict(String verdict) { this.verdict = verdict; }

    public List<String> getPracticalTips() { return practicalTips; }
    public void setPracticalTips(List<String> practicalTips) { this.practicalTips = practicalTips; }

    public static WaterPlanResponseBuilder builder() { return new WaterPlanResponseBuilder(); }

    public static class WaterPlanResponseBuilder {
        private String district;
        private String cropName;
        private String season;
        private List<MonthBudget> monthlyPlan;
        private Double totalHarvestLitres;
        private Double totalDemandLitres;
        private String verdict;
        private List<String> practicalTips;

        public WaterPlanResponseBuilder district(String district) { this.district = district; return this; }
        public WaterPlanResponseBuilder cropName(String cropName) { this.cropName = cropName; return this; }
        public WaterPlanResponseBuilder season(String season) { this.season = season; return this; }
        public WaterPlanResponseBuilder monthlyPlan(List<MonthBudget> monthlyPlan) { this.monthlyPlan = monthlyPlan; return this; }
        public WaterPlanResponseBuilder totalHarvestLitres(Double totalHarvestLitres) { this.totalHarvestLitres = totalHarvestLitres; return this; }
        public WaterPlanResponseBuilder totalDemandLitres(Double totalDemandLitres) { this.totalDemandLitres = totalDemandLitres; return this; }
        public WaterPlanResponseBuilder verdict(String verdict) { this.verdict = verdict; return this; }
        public WaterPlanResponseBuilder practicalTips(List<String> practicalTips) { this.practicalTips = practicalTips; return this; }

        public WaterPlanResponse build() {
            return new WaterPlanResponse(district, cropName, season, monthlyPlan, totalHarvestLitres, totalDemandLitres, verdict, practicalTips);
        }
    }
}
