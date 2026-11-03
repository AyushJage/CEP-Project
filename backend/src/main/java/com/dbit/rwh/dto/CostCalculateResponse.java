package com.dbit.rwh.dto;


public class CostCalculateResponse {
    private Double tankerSavingsYearly;
    private Double pumpingSavingsYearly;
    private Double totalSavingsYearly;
    private Double monthlySavings;
    private Double paybackPeriodYears;
    private String paybackVerdict;
    private String paybackVerdictMr;
    private String paybackVerdictEn;

    public CostCalculateResponse() {}

    public CostCalculateResponse(Double tankerSavingsYearly, Double pumpingSavingsYearly, Double totalSavingsYearly, Double monthlySavings, Double paybackPeriodYears, String paybackVerdict) {
        this.tankerSavingsYearly = tankerSavingsYearly;
        this.pumpingSavingsYearly = pumpingSavingsYearly;
        this.totalSavingsYearly = totalSavingsYearly;
        this.monthlySavings = monthlySavings;
        this.paybackPeriodYears = paybackPeriodYears;
        this.paybackVerdict = paybackVerdict;
    }

    public Double getTankerSavingsYearly() {
        return this.tankerSavingsYearly;
    }

    public void setTankerSavingsYearly(Double tankerSavingsYearly) {
        this.tankerSavingsYearly = tankerSavingsYearly;
    }

    public Double getPumpingSavingsYearly() {
        return this.pumpingSavingsYearly;
    }

    public void setPumpingSavingsYearly(Double pumpingSavingsYearly) {
        this.pumpingSavingsYearly = pumpingSavingsYearly;
    }

    public Double getTotalSavingsYearly() {
        return this.totalSavingsYearly;
    }

    public void setTotalSavingsYearly(Double totalSavingsYearly) {
        this.totalSavingsYearly = totalSavingsYearly;
    }

    public Double getMonthlySavings() {
        return this.monthlySavings;
    }

    public void setMonthlySavings(Double monthlySavings) {
        this.monthlySavings = monthlySavings;
    }

    public Double getPaybackPeriodYears() {
        return this.paybackPeriodYears;
    }

    public void setPaybackPeriodYears(Double paybackPeriodYears) {
        this.paybackPeriodYears = paybackPeriodYears;
    }

    public String getPaybackVerdict() {
        return this.paybackVerdict;
    }

    public void setPaybackVerdict(String paybackVerdict) {
        this.paybackVerdict = paybackVerdict;
    }

    public String getPaybackVerdictMr() {
        return this.paybackVerdictMr;
    }

    public void setPaybackVerdictMr(String paybackVerdictMr) {
        this.paybackVerdictMr = paybackVerdictMr;
    }

    public String getPaybackVerdictEn() {
        return this.paybackVerdictEn;
    }

    public void setPaybackVerdictEn(String paybackVerdictEn) {
        this.paybackVerdictEn = paybackVerdictEn;
    }

    public static CostCalculateResponseBuilder builder() {
        return new CostCalculateResponseBuilder();
    }

    public static class CostCalculateResponseBuilder {
        private Double tankerSavingsYearly;
        private Double pumpingSavingsYearly;
        private Double totalSavingsYearly;
        private Double monthlySavings;
        private Double paybackPeriodYears;
        private String paybackVerdict;
        private String paybackVerdictMr;
        private String paybackVerdictEn;

        public CostCalculateResponseBuilder tankerSavingsYearly(Double tankerSavingsYearly) {
            this.tankerSavingsYearly = tankerSavingsYearly;
            return this;
        }

        public CostCalculateResponseBuilder pumpingSavingsYearly(Double pumpingSavingsYearly) {
            this.pumpingSavingsYearly = pumpingSavingsYearly;
            return this;
        }

        public CostCalculateResponseBuilder totalSavingsYearly(Double totalSavingsYearly) {
            this.totalSavingsYearly = totalSavingsYearly;
            return this;
        }

        public CostCalculateResponseBuilder monthlySavings(Double monthlySavings) {
            this.monthlySavings = monthlySavings;
            return this;
        }

        public CostCalculateResponseBuilder paybackPeriodYears(Double paybackPeriodYears) {
            this.paybackPeriodYears = paybackPeriodYears;
            return this;
        }

        public CostCalculateResponseBuilder paybackVerdict(String paybackVerdict) {
            this.paybackVerdict = paybackVerdict;
            return this;
        }

        public CostCalculateResponseBuilder paybackVerdictMr(String paybackVerdictMr) {
            this.paybackVerdictMr = paybackVerdictMr;
            return this;
        }

        public CostCalculateResponseBuilder paybackVerdictEn(String paybackVerdictEn) {
            this.paybackVerdictEn = paybackVerdictEn;
            return this;
        }

        public CostCalculateResponse build() {
            CostCalculateResponse instance = new CostCalculateResponse();
            instance.setTankerSavingsYearly(this.tankerSavingsYearly);
            instance.setPumpingSavingsYearly(this.pumpingSavingsYearly);
            instance.setTotalSavingsYearly(this.totalSavingsYearly);
            instance.setMonthlySavings(this.monthlySavings);
            instance.setPaybackPeriodYears(this.paybackPeriodYears);
            instance.setPaybackVerdict(this.paybackVerdict);
            instance.setPaybackVerdictMr(this.paybackVerdictMr);
            instance.setPaybackVerdictEn(this.paybackVerdictEn);
            return instance;
        }
    }

}
