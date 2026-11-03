package com.dbit.rwh.dto;


public class FarmerProfileDto {
    private Double landAreaSqm;
    private String irrigationSource;
    private String rwhStructure;
    private String mainCrop;
    private String soilType;
    private Long assignedFieldWorkerId;
    private String assignedFieldWorkerName;

    public FarmerProfileDto() {}

    public FarmerProfileDto(Double landAreaSqm, String irrigationSource, String rwhStructure, String mainCrop, String soilType, Long assignedFieldWorkerId, String assignedFieldWorkerName) {
        this.landAreaSqm = landAreaSqm;
        this.irrigationSource = irrigationSource;
        this.rwhStructure = rwhStructure;
        this.mainCrop = mainCrop;
        this.soilType = soilType;
        this.assignedFieldWorkerId = assignedFieldWorkerId;
        this.assignedFieldWorkerName = assignedFieldWorkerName;
    }

    public Double getLandAreaSqm() {
        return this.landAreaSqm;
    }

    public void setLandAreaSqm(Double landAreaSqm) {
        this.landAreaSqm = landAreaSqm;
    }

    public String getIrrigationSource() {
        return this.irrigationSource;
    }

    public void setIrrigationSource(String irrigationSource) {
        this.irrigationSource = irrigationSource;
    }

    public String getRwhStructure() {
        return this.rwhStructure;
    }

    public void setRwhStructure(String rwhStructure) {
        this.rwhStructure = rwhStructure;
    }

    public String getMainCrop() {
        return this.mainCrop;
    }

    public void setMainCrop(String mainCrop) {
        this.mainCrop = mainCrop;
    }

    public String getSoilType() {
        return this.soilType;
    }

    public void setSoilType(String soilType) {
        this.soilType = soilType;
    }

    public Long getAssignedFieldWorkerId() {
        return this.assignedFieldWorkerId;
    }

    public void setAssignedFieldWorkerId(Long assignedFieldWorkerId) {
        this.assignedFieldWorkerId = assignedFieldWorkerId;
    }

    public String getAssignedFieldWorkerName() {
        return this.assignedFieldWorkerName;
    }

    public void setAssignedFieldWorkerName(String assignedFieldWorkerName) {
        this.assignedFieldWorkerName = assignedFieldWorkerName;
    }

    public static FarmerProfileDtoBuilder builder() {
        return new FarmerProfileDtoBuilder();
    }

    public static class FarmerProfileDtoBuilder {
        private Double landAreaSqm;
        private String irrigationSource;
        private String rwhStructure;
        private String mainCrop;
        private String soilType;
        private Long assignedFieldWorkerId;
        private String assignedFieldWorkerName;

        public FarmerProfileDtoBuilder landAreaSqm(Double landAreaSqm) {
            this.landAreaSqm = landAreaSqm;
            return this;
        }

        public FarmerProfileDtoBuilder irrigationSource(String irrigationSource) {
            this.irrigationSource = irrigationSource;
            return this;
        }

        public FarmerProfileDtoBuilder rwhStructure(String rwhStructure) {
            this.rwhStructure = rwhStructure;
            return this;
        }

        public FarmerProfileDtoBuilder mainCrop(String mainCrop) {
            this.mainCrop = mainCrop;
            return this;
        }

        public FarmerProfileDtoBuilder soilType(String soilType) {
            this.soilType = soilType;
            return this;
        }

        public FarmerProfileDtoBuilder assignedFieldWorkerId(Long assignedFieldWorkerId) {
            this.assignedFieldWorkerId = assignedFieldWorkerId;
            return this;
        }

        public FarmerProfileDtoBuilder assignedFieldWorkerName(String assignedFieldWorkerName) {
            this.assignedFieldWorkerName = assignedFieldWorkerName;
            return this;
        }

        public FarmerProfileDto build() {
            FarmerProfileDto instance = new FarmerProfileDto();
            instance.setLandAreaSqm(this.landAreaSqm);
            instance.setIrrigationSource(this.irrigationSource);
            instance.setRwhStructure(this.rwhStructure);
            instance.setMainCrop(this.mainCrop);
            instance.setSoilType(this.soilType);
            instance.setAssignedFieldWorkerId(this.assignedFieldWorkerId);
            instance.setAssignedFieldWorkerName(this.assignedFieldWorkerName);
            return instance;
        }
    }

}
