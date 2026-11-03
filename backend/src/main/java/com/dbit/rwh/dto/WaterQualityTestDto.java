package com.dbit.rwh.dto;

import java.time.LocalDate;
import java.util.List;

public class WaterQualityTestDto {
    private Long id;
    private Long tankId;
    private String tankName;
    private LocalDate testDate;
    private Double ph;
    private Double tdsOrConductivity;
    private Double turbidity;
    private Double nitrate;
    private Boolean coliformPresent;
    private String verdictDrinking; // Safe, Caution, Not safe
    private String verdictIrrigation; // Safe, Caution, Not safe
    private List<String> advice;

    public WaterQualityTestDto() {}

    public WaterQualityTestDto(Long id, Long tankId, String tankName, LocalDate testDate, Double ph, Double tdsOrConductivity, Double turbidity, Double nitrate, Boolean coliformPresent, String verdictDrinking, String verdictIrrigation, List<String> advice) {
        this.id = id;
        this.tankId = tankId;
        this.tankName = tankName;
        this.testDate = testDate;
        this.ph = ph;
        this.tdsOrConductivity = tdsOrConductivity;
        this.turbidity = turbidity;
        this.nitrate = nitrate;
        this.coliformPresent = coliformPresent;
        this.verdictDrinking = verdictDrinking;
        this.verdictIrrigation = verdictIrrigation;
        this.advice = advice;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTankId() {
        return this.tankId;
    }

    public void setTankId(Long tankId) {
        this.tankId = tankId;
    }

    public String getTankName() {
        return this.tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public LocalDate getTestDate() {
        return this.testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public Double getPh() {
        return this.ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getTdsOrConductivity() {
        return this.tdsOrConductivity;
    }

    public void setTdsOrConductivity(Double tdsOrConductivity) {
        this.tdsOrConductivity = tdsOrConductivity;
    }

    public Double getTurbidity() {
        return this.turbidity;
    }

    public void setTurbidity(Double turbidity) {
        this.turbidity = turbidity;
    }

    public Double getNitrate() {
        return this.nitrate;
    }

    public void setNitrate(Double nitrate) {
        this.nitrate = nitrate;
    }

    public Boolean getColiformPresent() {
        return this.coliformPresent;
    }

    public Boolean isColiformPresent() {
        return this.coliformPresent;
    }

    public void setColiformPresent(Boolean coliformPresent) {
        this.coliformPresent = coliformPresent;
    }

    public String getVerdictDrinking() {
        return this.verdictDrinking;
    }

    public void setVerdictDrinking(String verdictDrinking) {
        this.verdictDrinking = verdictDrinking;
    }

    public String getVerdictIrrigation() {
        return this.verdictIrrigation;
    }

    public void setVerdictIrrigation(String verdictIrrigation) {
        this.verdictIrrigation = verdictIrrigation;
    }

    public List<String> getAdvice() {
        return this.advice;
    }

    public void setAdvice(List<String> advice) {
        this.advice = advice;
    }

    public static WaterQualityTestDtoBuilder builder() {
        return new WaterQualityTestDtoBuilder();
    }

    public static class WaterQualityTestDtoBuilder {
        private Long id;
        private Long tankId;
        private String tankName;
        private LocalDate testDate;
        private Double ph;
        private Double tdsOrConductivity;
        private Double turbidity;
        private Double nitrate;
        private Boolean coliformPresent;
        private String verdictDrinking;
        private String verdictIrrigation;
        private List<String> advice;

        public WaterQualityTestDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WaterQualityTestDtoBuilder tankId(Long tankId) {
            this.tankId = tankId;
            return this;
        }

        public WaterQualityTestDtoBuilder tankName(String tankName) {
            this.tankName = tankName;
            return this;
        }

        public WaterQualityTestDtoBuilder testDate(LocalDate testDate) {
            this.testDate = testDate;
            return this;
        }

        public WaterQualityTestDtoBuilder ph(Double ph) {
            this.ph = ph;
            return this;
        }

        public WaterQualityTestDtoBuilder tdsOrConductivity(Double tdsOrConductivity) {
            this.tdsOrConductivity = tdsOrConductivity;
            return this;
        }

        public WaterQualityTestDtoBuilder turbidity(Double turbidity) {
            this.turbidity = turbidity;
            return this;
        }

        public WaterQualityTestDtoBuilder nitrate(Double nitrate) {
            this.nitrate = nitrate;
            return this;
        }

        public WaterQualityTestDtoBuilder coliformPresent(Boolean coliformPresent) {
            this.coliformPresent = coliformPresent;
            return this;
        }

        public WaterQualityTestDtoBuilder verdictDrinking(String verdictDrinking) {
            this.verdictDrinking = verdictDrinking;
            return this;
        }

        public WaterQualityTestDtoBuilder verdictIrrigation(String verdictIrrigation) {
            this.verdictIrrigation = verdictIrrigation;
            return this;
        }

        public WaterQualityTestDtoBuilder advice(List<String> advice) {
            this.advice = advice;
            return this;
        }

        public WaterQualityTestDto build() {
            WaterQualityTestDto instance = new WaterQualityTestDto();
            instance.setId(this.id);
            instance.setTankId(this.tankId);
            instance.setTankName(this.tankName);
            instance.setTestDate(this.testDate);
            instance.setPh(this.ph);
            instance.setTdsOrConductivity(this.tdsOrConductivity);
            instance.setTurbidity(this.turbidity);
            instance.setNitrate(this.nitrate);
            instance.setColiformPresent(this.coliformPresent);
            instance.setVerdictDrinking(this.verdictDrinking);
            instance.setVerdictIrrigation(this.verdictIrrigation);
            instance.setAdvice(this.advice);
            return instance;
        }
    }

}
