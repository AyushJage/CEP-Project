package com.dbit.rwh.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "district_rainfall_normal")
public class DistrictRainfallNormal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String district;

    private Double jan;
    private Double feb;
    private Double mar;
    private Double apr;
    private Double may;
    private Double jun;
    private Double jul;
    private Double aug;
    private Double sep;
    private Double oct;
    private Double nov;
    private Double dec;

    @Column(name = "annual_mm")
    private Double annualMm;

    public DistrictRainfallNormal() {}

    public DistrictRainfallNormal(Long id, String district, Double jan, Double feb, Double mar, Double apr, Double may, Double jun, Double jul, Double aug, Double sep, Double oct, Double nov, Double dec, Double annualMm) {
        this.id = id;
        this.district = district;
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
        this.annualMm = annualMm;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Double getJan() {
        return this.jan;
    }

    public void setJan(Double jan) {
        this.jan = jan;
    }

    public Double getFeb() {
        return this.feb;
    }

    public void setFeb(Double feb) {
        this.feb = feb;
    }

    public Double getMar() {
        return this.mar;
    }

    public void setMar(Double mar) {
        this.mar = mar;
    }

    public Double getApr() {
        return this.apr;
    }

    public void setApr(Double apr) {
        this.apr = apr;
    }

    public Double getMay() {
        return this.may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJun() {
        return this.jun;
    }

    public void setJun(Double jun) {
        this.jun = jun;
    }

    public Double getJul() {
        return this.jul;
    }

    public void setJul(Double jul) {
        this.jul = jul;
    }

    public Double getAug() {
        return this.aug;
    }

    public void setAug(Double aug) {
        this.aug = aug;
    }

    public Double getSep() {
        return this.sep;
    }

    public void setSep(Double sep) {
        this.sep = sep;
    }

    public Double getOct() {
        return this.oct;
    }

    public void setOct(Double oct) {
        this.oct = oct;
    }

    public Double getNov() {
        return this.nov;
    }

    public void setNov(Double nov) {
        this.nov = nov;
    }

    public Double getDec() {
        return this.dec;
    }

    public void setDec(Double dec) {
        this.dec = dec;
    }

    public Double getAnnualMm() {
        return this.annualMm;
    }

    public void setAnnualMm(Double annualMm) {
        this.annualMm = annualMm;
    }

    public static DistrictRainfallNormalBuilder builder() {
        return new DistrictRainfallNormalBuilder();
    }

    public static class DistrictRainfallNormalBuilder {
        private Long id;
        private String district;
        private Double jan;
        private Double feb;
        private Double mar;
        private Double apr;
        private Double may;
        private Double jun;
        private Double jul;
        private Double aug;
        private Double sep;
        private Double oct;
        private Double nov;
        private Double dec;
        private Double annualMm;

        public DistrictRainfallNormalBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DistrictRainfallNormalBuilder district(String district) {
            this.district = district;
            return this;
        }

        public DistrictRainfallNormalBuilder jan(Double jan) {
            this.jan = jan;
            return this;
        }

        public DistrictRainfallNormalBuilder feb(Double feb) {
            this.feb = feb;
            return this;
        }

        public DistrictRainfallNormalBuilder mar(Double mar) {
            this.mar = mar;
            return this;
        }

        public DistrictRainfallNormalBuilder apr(Double apr) {
            this.apr = apr;
            return this;
        }

        public DistrictRainfallNormalBuilder may(Double may) {
            this.may = may;
            return this;
        }

        public DistrictRainfallNormalBuilder jun(Double jun) {
            this.jun = jun;
            return this;
        }

        public DistrictRainfallNormalBuilder jul(Double jul) {
            this.jul = jul;
            return this;
        }

        public DistrictRainfallNormalBuilder aug(Double aug) {
            this.aug = aug;
            return this;
        }

        public DistrictRainfallNormalBuilder sep(Double sep) {
            this.sep = sep;
            return this;
        }

        public DistrictRainfallNormalBuilder oct(Double oct) {
            this.oct = oct;
            return this;
        }

        public DistrictRainfallNormalBuilder nov(Double nov) {
            this.nov = nov;
            return this;
        }

        public DistrictRainfallNormalBuilder dec(Double dec) {
            this.dec = dec;
            return this;
        }

        public DistrictRainfallNormalBuilder annualMm(Double annualMm) {
            this.annualMm = annualMm;
            return this;
        }

        public DistrictRainfallNormal build() {
            DistrictRainfallNormal instance = new DistrictRainfallNormal();
            instance.setId(this.id);
            instance.setDistrict(this.district);
            instance.setJan(this.jan);
            instance.setFeb(this.feb);
            instance.setMar(this.mar);
            instance.setApr(this.apr);
            instance.setMay(this.may);
            instance.setJun(this.jun);
            instance.setJul(this.jul);
            instance.setAug(this.aug);
            instance.setSep(this.sep);
            instance.setOct(this.oct);
            instance.setNov(this.nov);
            instance.setDec(this.dec);
            instance.setAnnualMm(this.annualMm);
            return instance;
        }
    }

}
