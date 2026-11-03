package com.dbit.rwh.dto;


public class HarvestCalculateResponse {
    private Double catchmentAreaSqm;
    private Double rainfallMm;
    private Double runoffCoefficient;
    private String surfaceType;
    private Double harvestedLitres;
    private Double harvestedCubicMetres;
    private Double thousandLitreTanksFilled;

    public HarvestCalculateResponse() {}

    public HarvestCalculateResponse(Double catchmentAreaSqm, Double rainfallMm, Double runoffCoefficient, String surfaceType, Double harvestedLitres, Double harvestedCubicMetres, Double thousandLitreTanksFilled) {
        this.catchmentAreaSqm = catchmentAreaSqm;
        this.rainfallMm = rainfallMm;
        this.runoffCoefficient = runoffCoefficient;
        this.surfaceType = surfaceType;
        this.harvestedLitres = harvestedLitres;
        this.harvestedCubicMetres = harvestedCubicMetres;
        this.thousandLitreTanksFilled = thousandLitreTanksFilled;
    }

    public Double getCatchmentAreaSqm() {
        return this.catchmentAreaSqm;
    }

    public void setCatchmentAreaSqm(Double catchmentAreaSqm) {
        this.catchmentAreaSqm = catchmentAreaSqm;
    }

    public Double getRainfallMm() {
        return this.rainfallMm;
    }

    public void setRainfallMm(Double rainfallMm) {
        this.rainfallMm = rainfallMm;
    }

    public Double getRunoffCoefficient() {
        return this.runoffCoefficient;
    }

    public void setRunoffCoefficient(Double runoffCoefficient) {
        this.runoffCoefficient = runoffCoefficient;
    }

    public String getSurfaceType() {
        return this.surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = surfaceType;
    }

    public Double getHarvestedLitres() {
        return this.harvestedLitres;
    }

    public void setHarvestedLitres(Double harvestedLitres) {
        this.harvestedLitres = harvestedLitres;
    }

    public Double getHarvestedCubicMetres() {
        return this.harvestedCubicMetres;
    }

    public void setHarvestedCubicMetres(Double harvestedCubicMetres) {
        this.harvestedCubicMetres = harvestedCubicMetres;
    }

    public Double getThousandLitreTanksFilled() {
        return this.thousandLitreTanksFilled;
    }

    public void setThousandLitreTanksFilled(Double thousandLitreTanksFilled) {
        this.thousandLitreTanksFilled = thousandLitreTanksFilled;
    }

    public static HarvestCalculateResponseBuilder builder() {
        return new HarvestCalculateResponseBuilder();
    }

    public static class HarvestCalculateResponseBuilder {
        private Double catchmentAreaSqm;
        private Double rainfallMm;
        private Double runoffCoefficient;
        private String surfaceType;
        private Double harvestedLitres;
        private Double harvestedCubicMetres;
        private Double thousandLitreTanksFilled;

        public HarvestCalculateResponseBuilder catchmentAreaSqm(Double catchmentAreaSqm) {
            this.catchmentAreaSqm = catchmentAreaSqm;
            return this;
        }

        public HarvestCalculateResponseBuilder rainfallMm(Double rainfallMm) {
            this.rainfallMm = rainfallMm;
            return this;
        }

        public HarvestCalculateResponseBuilder runoffCoefficient(Double runoffCoefficient) {
            this.runoffCoefficient = runoffCoefficient;
            return this;
        }

        public HarvestCalculateResponseBuilder surfaceType(String surfaceType) {
            this.surfaceType = surfaceType;
            return this;
        }

        public HarvestCalculateResponseBuilder harvestedLitres(Double harvestedLitres) {
            this.harvestedLitres = harvestedLitres;
            return this;
        }

        public HarvestCalculateResponseBuilder harvestedCubicMetres(Double harvestedCubicMetres) {
            this.harvestedCubicMetres = harvestedCubicMetres;
            return this;
        }

        public HarvestCalculateResponseBuilder thousandLitreTanksFilled(Double thousandLitreTanksFilled) {
            this.thousandLitreTanksFilled = thousandLitreTanksFilled;
            return this;
        }

        public HarvestCalculateResponse build() {
            HarvestCalculateResponse instance = new HarvestCalculateResponse();
            instance.setCatchmentAreaSqm(this.catchmentAreaSqm);
            instance.setRainfallMm(this.rainfallMm);
            instance.setRunoffCoefficient(this.runoffCoefficient);
            instance.setSurfaceType(this.surfaceType);
            instance.setHarvestedLitres(this.harvestedLitres);
            instance.setHarvestedCubicMetres(this.harvestedCubicMetres);
            instance.setThousandLitreTanksFilled(this.thousandLitreTanksFilled);
            return instance;
        }
    }

}
