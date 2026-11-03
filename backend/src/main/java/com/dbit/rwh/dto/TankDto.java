package com.dbit.rwh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TankDto {
    private Long id;

    @NotBlank(message = "Tank name is required")
    private String name;

    @NotBlank(message = "Tank type is required")
    private String type;

    @NotNull(message = "Capacity in litres is required")
    @Positive(message = "Capacity must be positive")
    private Double capacityLitres;

    private Double currentLevelLitres;
    private Double levelPercentage;
    private String statusColor; // green (>50%), amber (20-50%), red (<20%)

    public TankDto() {}

    public TankDto(Long id, String name, String type, Double capacityLitres, Double currentLevelLitres, Double levelPercentage, String statusColor) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.capacityLitres = capacityLitres;
        this.currentLevelLitres = currentLevelLitres;
        this.levelPercentage = levelPercentage;
        this.statusColor = statusColor;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCapacityLitres() {
        return this.capacityLitres;
    }

    public void setCapacityLitres(Double capacityLitres) {
        this.capacityLitres = capacityLitres;
    }

    public Double getCurrentLevelLitres() {
        return this.currentLevelLitres;
    }

    public void setCurrentLevelLitres(Double currentLevelLitres) {
        this.currentLevelLitres = currentLevelLitres;
    }

    public Double getLevelPercentage() {
        return this.levelPercentage;
    }

    public void setLevelPercentage(Double levelPercentage) {
        this.levelPercentage = levelPercentage;
    }

    public String getStatusColor() {
        return this.statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }

    public static TankDtoBuilder builder() {
        return new TankDtoBuilder();
    }

    public static class TankDtoBuilder {
        private Long id;
        private String name;
        private String type;
        private Double capacityLitres;
        private Double currentLevelLitres;
        private Double levelPercentage;
        private String statusColor;

        public TankDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TankDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TankDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public TankDtoBuilder capacityLitres(Double capacityLitres) {
            this.capacityLitres = capacityLitres;
            return this;
        }

        public TankDtoBuilder currentLevelLitres(Double currentLevelLitres) {
            this.currentLevelLitres = currentLevelLitres;
            return this;
        }

        public TankDtoBuilder levelPercentage(Double levelPercentage) {
            this.levelPercentage = levelPercentage;
            return this;
        }

        public TankDtoBuilder statusColor(String statusColor) {
            this.statusColor = statusColor;
            return this;
        }

        public TankDto build() {
            TankDto instance = new TankDto();
            instance.setId(this.id);
            instance.setName(this.name);
            instance.setType(this.type);
            instance.setCapacityLitres(this.capacityLitres);
            instance.setCurrentLevelLitres(this.currentLevelLitres);
            instance.setLevelPercentage(this.levelPercentage);
            instance.setStatusColor(this.statusColor);
            return instance;
        }
    }

}
