package com.dbit.rwh.dto;

import java.util.Map;

public class AiForecastResponse {
    private String district;
    private String region;
    private String subdivision;
    private Double predictedRainfallMm;
    private Double historicalNormalMm;
    private Double minExpectedMm;
    private Double maxExpectedMm;
    private String comparisonLabel; // below normal, near normal, above normal
    private Double confidence;
    private String explanation;
    private Map<String, Double> monthlyTrend;

    public AiForecastResponse() {}

    public AiForecastResponse(String district, String region, String subdivision, Double predictedRainfallMm, Double historicalNormalMm, Double minExpectedMm, Double maxExpectedMm, String comparisonLabel, Double confidence, String explanation, Map<String, Double> monthlyTrend) {
        this.district = district;
        this.region = region;
        this.subdivision = subdivision;
        this.predictedRainfallMm = predictedRainfallMm;
        this.historicalNormalMm = historicalNormalMm;
        this.minExpectedMm = minExpectedMm;
        this.maxExpectedMm = maxExpectedMm;
        this.comparisonLabel = comparisonLabel;
        this.confidence = confidence;
        this.explanation = explanation;
        this.monthlyTrend = monthlyTrend;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubdivision() {
        return this.subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public Double getPredictedRainfallMm() {
        return this.predictedRainfallMm;
    }

    public void setPredictedRainfallMm(Double predictedRainfallMm) {
        this.predictedRainfallMm = predictedRainfallMm;
    }

    public Double getHistoricalNormalMm() {
        return this.historicalNormalMm;
    }

    public void setHistoricalNormalMm(Double historicalNormalMm) {
        this.historicalNormalMm = historicalNormalMm;
    }

    public Double getMinExpectedMm() {
        return this.minExpectedMm;
    }

    public void setMinExpectedMm(Double minExpectedMm) {
        this.minExpectedMm = minExpectedMm;
    }

    public Double getMaxExpectedMm() {
        return this.maxExpectedMm;
    }

    public void setMaxExpectedMm(Double maxExpectedMm) {
        this.maxExpectedMm = maxExpectedMm;
    }

    public String getComparisonLabel() {
        return this.comparisonLabel;
    }

    public void setComparisonLabel(String comparisonLabel) {
        this.comparisonLabel = comparisonLabel;
    }

    public Double getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Map<String, Double> getMonthlyTrend() {
        return this.monthlyTrend;
    }

    public void setMonthlyTrend(Map<String, Double> monthlyTrend) {
        this.monthlyTrend = monthlyTrend;
    }

    public static AiForecastResponseBuilder builder() {
        return new AiForecastResponseBuilder();
    }

    public static class AiForecastResponseBuilder {
        private String district;
        private String region;
        private String subdivision;
        private Double predictedRainfallMm;
        private Double historicalNormalMm;
        private Double minExpectedMm;
        private Double maxExpectedMm;
        private String comparisonLabel;
        private Double confidence;
        private String explanation;
        private Map<String, Double> monthlyTrend;

        public AiForecastResponseBuilder district(String district) {
            this.district = district;
            return this;
        }

        public AiForecastResponseBuilder region(String region) {
            this.region = region;
            return this;
        }

        public AiForecastResponseBuilder subdivision(String subdivision) {
            this.subdivision = subdivision;
            return this;
        }

        public AiForecastResponseBuilder predictedRainfallMm(Double predictedRainfallMm) {
            this.predictedRainfallMm = predictedRainfallMm;
            return this;
        }

        public AiForecastResponseBuilder historicalNormalMm(Double historicalNormalMm) {
            this.historicalNormalMm = historicalNormalMm;
            return this;
        }

        public AiForecastResponseBuilder minExpectedMm(Double minExpectedMm) {
            this.minExpectedMm = minExpectedMm;
            return this;
        }

        public AiForecastResponseBuilder maxExpectedMm(Double maxExpectedMm) {
            this.maxExpectedMm = maxExpectedMm;
            return this;
        }

        public AiForecastResponseBuilder comparisonLabel(String comparisonLabel) {
            this.comparisonLabel = comparisonLabel;
            return this;
        }

        public AiForecastResponseBuilder confidence(Double confidence) {
            this.confidence = confidence;
            return this;
        }

        public AiForecastResponseBuilder explanation(String explanation) {
            this.explanation = explanation;
            return this;
        }

        public AiForecastResponseBuilder monthlyTrend(Map<String, Double> monthlyTrend) {
            this.monthlyTrend = monthlyTrend;
            return this;
        }

        public AiForecastResponse build() {
            AiForecastResponse instance = new AiForecastResponse();
            instance.setDistrict(this.district);
            instance.setRegion(this.region);
            instance.setSubdivision(this.subdivision);
            instance.setPredictedRainfallMm(this.predictedRainfallMm);
            instance.setHistoricalNormalMm(this.historicalNormalMm);
            instance.setMinExpectedMm(this.minExpectedMm);
            instance.setMaxExpectedMm(this.maxExpectedMm);
            instance.setComparisonLabel(this.comparisonLabel);
            instance.setConfidence(this.confidence);
            instance.setExplanation(this.explanation);
            instance.setMonthlyTrend(this.monthlyTrend);
            return instance;
        }
    }

}
