package com.dbit.rwh.dto;

import java.util.List;

public class RecommendationDto {
    private String structureType;
    private Double suggestedCapacityLitres;
    private String reasonSummary;
    private String lowCostOption;
    private String mechanisedOption;
    private List<String> prioritisedTips;

    public RecommendationDto() {}

    public RecommendationDto(String structureType, Double suggestedCapacityLitres, String reasonSummary, String lowCostOption, String mechanisedOption, List<String> prioritisedTips) {
        this.structureType = structureType;
        this.suggestedCapacityLitres = suggestedCapacityLitres;
        this.reasonSummary = reasonSummary;
        this.lowCostOption = lowCostOption;
        this.mechanisedOption = mechanisedOption;
        this.prioritisedTips = prioritisedTips;
    }

    public String getStructureType() {
        return this.structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public Double getSuggestedCapacityLitres() {
        return this.suggestedCapacityLitres;
    }

    public void setSuggestedCapacityLitres(Double suggestedCapacityLitres) {
        this.suggestedCapacityLitres = suggestedCapacityLitres;
    }

    public String getReasonSummary() {
        return this.reasonSummary;
    }

    public void setReasonSummary(String reasonSummary) {
        this.reasonSummary = reasonSummary;
    }

    public String getLowCostOption() {
        return this.lowCostOption;
    }

    public void setLowCostOption(String lowCostOption) {
        this.lowCostOption = lowCostOption;
    }

    public String getMechanisedOption() {
        return this.mechanisedOption;
    }

    public void setMechanisedOption(String mechanisedOption) {
        this.mechanisedOption = mechanisedOption;
    }

    public List<String> getPrioritisedTips() {
        return this.prioritisedTips;
    }

    public void setPrioritisedTips(List<String> prioritisedTips) {
        this.prioritisedTips = prioritisedTips;
    }

    public static RecommendationDtoBuilder builder() {
        return new RecommendationDtoBuilder();
    }

    public static class RecommendationDtoBuilder {
        private String structureType;
        private Double suggestedCapacityLitres;
        private String reasonSummary;
        private String lowCostOption;
        private String mechanisedOption;
        private List<String> prioritisedTips;

        public RecommendationDtoBuilder structureType(String structureType) {
            this.structureType = structureType;
            return this;
        }

        public RecommendationDtoBuilder suggestedCapacityLitres(Double suggestedCapacityLitres) {
            this.suggestedCapacityLitres = suggestedCapacityLitres;
            return this;
        }

        public RecommendationDtoBuilder reasonSummary(String reasonSummary) {
            this.reasonSummary = reasonSummary;
            return this;
        }

        public RecommendationDtoBuilder lowCostOption(String lowCostOption) {
            this.lowCostOption = lowCostOption;
            return this;
        }

        public RecommendationDtoBuilder mechanisedOption(String mechanisedOption) {
            this.mechanisedOption = mechanisedOption;
            return this;
        }

        public RecommendationDtoBuilder prioritisedTips(List<String> prioritisedTips) {
            this.prioritisedTips = prioritisedTips;
            return this;
        }

        public RecommendationDto build() {
            RecommendationDto instance = new RecommendationDto();
            instance.setStructureType(this.structureType);
            instance.setSuggestedCapacityLitres(this.suggestedCapacityLitres);
            instance.setReasonSummary(this.reasonSummary);
            instance.setLowCostOption(this.lowCostOption);
            instance.setMechanisedOption(this.mechanisedOption);
            instance.setPrioritisedTips(this.prioritisedTips);
            return instance;
        }
    }

}
