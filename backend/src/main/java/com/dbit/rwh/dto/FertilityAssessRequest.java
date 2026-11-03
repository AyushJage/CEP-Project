package com.dbit.rwh.dto;

import java.util.List;

public class FertilityAssessRequest {
    private Double ph;
    private Double nitrogen;       // kg/ha
    private Double phosphorus;     // kg/ha
    private Double potassium;      // kg/ha
    private Double organicCarbon;  // %
    private Double ec;             // dS/m

    public FertilityAssessRequest() {}

    public FertilityAssessRequest(Double ph, Double nitrogen, Double phosphorus, Double potassium, Double organicCarbon, Double ec) {
        this.ph = ph;
        this.nitrogen = nitrogen;
        this.phosphorus = phosphorus;
        this.potassium = potassium;
        this.organicCarbon = organicCarbon;
        this.ec = ec;
    }

    public Double getPh() {
        return this.ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getNitrogen() {
        return this.nitrogen;
    }

    public void setNitrogen(Double nitrogen) {
        this.nitrogen = nitrogen;
    }

    public Double getPhosphorus() {
        return this.phosphorus;
    }

    public void setPhosphorus(Double phosphorus) {
        this.phosphorus = phosphorus;
    }

    public Double getPotassium() {
        return this.potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getOrganicCarbon() {
        return this.organicCarbon;
    }

    public void setOrganicCarbon(Double organicCarbon) {
        this.organicCarbon = organicCarbon;
    }

    public Double getEc() {
        return this.ec;
    }

    public void setEc(Double ec) {
        this.ec = ec;
    }

}
