package com.dbit.rwh.dto;

import java.util.List;

public class CropSuggestionResponse {

    public static class SuggestedCrop {
        private String cropNameEn;
        private String cropNameMr;
        private Double probability;
        private String waterNeed;
        private Double approxWaterMm;
        private String season;
        private String explanation;

        public SuggestedCrop() {}

        public SuggestedCrop(String cropNameEn, String cropNameMr, Double probability, String waterNeed, Double approxWaterMm, String season, String explanation) {
            this.cropNameEn = cropNameEn;
            this.cropNameMr = cropNameMr;
            this.probability = probability;
            this.waterNeed = waterNeed;
            this.approxWaterMm = approxWaterMm;
            this.season = season;
            this.explanation = explanation;
        }

        public String getCropNameEn() { return cropNameEn; }
        public void setCropNameEn(String cropNameEn) { this.cropNameEn = cropNameEn; }

        public String getCropNameMr() { return cropNameMr; }
        public void setCropNameMr(String cropNameMr) { this.cropNameMr = cropNameMr; }

        public Double getProbability() { return probability; }
        public void setProbability(Double probability) { this.probability = probability; }

        public String getWaterNeed() { return waterNeed; }
        public void setWaterNeed(String waterNeed) { this.waterNeed = waterNeed; }

        public Double getApproxWaterMm() { return approxWaterMm; }
        public void setApproxWaterMm(Double approxWaterMm) { this.approxWaterMm = approxWaterMm; }

        public String getSeason() { return season; }
        public void setSeason(String season) { this.season = season; }

        public String getExplanation() { return explanation; }
        public void setExplanation(String explanation) { this.explanation = explanation; }

        public static SuggestedCropBuilder builder() { return new SuggestedCropBuilder(); }

        public static class SuggestedCropBuilder {
            private String cropNameEn;
            private String cropNameMr;
            private Double probability;
            private String waterNeed;
            private Double approxWaterMm;
            private String season;
            private String explanation;

            public SuggestedCropBuilder cropNameEn(String cropNameEn) { this.cropNameEn = cropNameEn; return this; }
            public SuggestedCropBuilder cropNameMr(String cropNameMr) { this.cropNameMr = cropNameMr; return this; }
            public SuggestedCropBuilder probability(Double probability) { this.probability = probability; return this; }
            public SuggestedCropBuilder waterNeed(String waterNeed) { this.waterNeed = waterNeed; return this; }
            public SuggestedCropBuilder approxWaterMm(Double approxWaterMm) { this.approxWaterMm = approxWaterMm; return this; }
            public SuggestedCropBuilder season(String season) { this.season = season; return this; }
            public SuggestedCropBuilder explanation(String explanation) { this.explanation = explanation; return this; }

            public SuggestedCrop build() {
                return new SuggestedCrop(cropNameEn, cropNameMr, probability, waterNeed, approxWaterMm, season, explanation);
            }
        }
    }

    private String district;
    private String region;
    private String season;
    private List<SuggestedCrop> suggestedCrops;
    private String soilFertilityStatus;
    private String waterAvailabilityVerdict;

    public CropSuggestionResponse() {}

    public CropSuggestionResponse(String district, String region, String season, List<SuggestedCrop> suggestedCrops, String soilFertilityStatus, String waterAvailabilityVerdict) {
        this.district = district;
        this.region = region;
        this.season = season;
        this.suggestedCrops = suggestedCrops;
        this.soilFertilityStatus = soilFertilityStatus;
        this.waterAvailabilityVerdict = waterAvailabilityVerdict;
    }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public List<SuggestedCrop> getSuggestedCrops() { return suggestedCrops; }
    public void setSuggestedCrops(List<SuggestedCrop> suggestedCrops) { this.suggestedCrops = suggestedCrops; }

    public List<SuggestedCrop> getTopCrops() { return suggestedCrops; }
    public void setTopCrops(List<SuggestedCrop> topCrops) { this.suggestedCrops = topCrops; }

    public String getSoilFertilityStatus() { return soilFertilityStatus; }
    public void setSoilFertilityStatus(String soilFertilityStatus) { this.soilFertilityStatus = soilFertilityStatus; }

    public String getWaterAvailabilityVerdict() { return waterAvailabilityVerdict; }
    public void setWaterAvailabilityVerdict(String waterAvailabilityVerdict) { this.waterAvailabilityVerdict = waterAvailabilityVerdict; }

    public static CropSuggestionResponseBuilder builder() { return new CropSuggestionResponseBuilder(); }

    public static class CropSuggestionResponseBuilder {
        private String district;
        private String region;
        private String season;
        private List<SuggestedCrop> suggestedCrops;
        private String soilFertilityStatus;
        private String waterAvailabilityVerdict;

        public CropSuggestionResponseBuilder district(String district) { this.district = district; return this; }
        public CropSuggestionResponseBuilder region(String region) { this.region = region; return this; }
        public CropSuggestionResponseBuilder agroRegion(String agroRegion) { this.region = agroRegion; return this; }
        public CropSuggestionResponseBuilder season(String season) { this.season = season; return this; }
        public CropSuggestionResponseBuilder suggestedCrops(List<SuggestedCrop> suggestedCrops) { this.suggestedCrops = suggestedCrops; return this; }
        public CropSuggestionResponseBuilder topCrops(List<SuggestedCrop> topCrops) { this.suggestedCrops = topCrops; return this; }
        public CropSuggestionResponseBuilder soilFertilityStatus(String soilFertilityStatus) { this.soilFertilityStatus = soilFertilityStatus; return this; }
        public CropSuggestionResponseBuilder waterAvailabilityVerdict(String waterAvailabilityVerdict) { this.waterAvailabilityVerdict = waterAvailabilityVerdict; return this; }
        public CropSuggestionResponseBuilder advisoryNote(String advisoryNote) { this.waterAvailabilityVerdict = advisoryNote; return this; }

        public CropSuggestionResponse build() {
            return new CropSuggestionResponse(district, region, season, suggestedCrops, soilFertilityStatus, waterAvailabilityVerdict);
        }
    }
}
