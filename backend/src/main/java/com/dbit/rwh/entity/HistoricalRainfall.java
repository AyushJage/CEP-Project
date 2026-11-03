package com.dbit.rwh.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "historical_rainfall")
public class HistoricalRainfall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String subdivision;

    @Column(name = "rec_year", nullable = false)
    private Integer year;

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

    public HistoricalRainfall() {}

    public HistoricalRainfall(Long id, String subdivision, Integer year, Double jan, Double feb, Double mar, Double apr, Double may, Double jun, Double jul, Double aug, Double sep, Double oct, Double nov, Double dec, Double annualMm) {
        this.id = id;
        this.subdivision = subdivision;
        this.year = year;
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

    public String getSubdivision() {
        return this.subdivision;
    }

    public void setSubdivision(String subdivision) {
        this.subdivision = subdivision;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public static HistoricalRainfallBuilder builder() {
        return new HistoricalRainfallBuilder();
    }

    public static class HistoricalRainfallBuilder {
        private Long id;
        private String subdivision;
        private Integer year;
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

        public HistoricalRainfallBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public HistoricalRainfallBuilder subdivision(String subdivision) {
            this.subdivision = subdivision;
            return this;
        }

        public HistoricalRainfallBuilder year(Integer year) {
            this.year = year;
            return this;
        }

        public HistoricalRainfallBuilder jan(Double jan) {
            this.jan = jan;
            return this;
        }

        public HistoricalRainfallBuilder feb(Double feb) {
            this.feb = feb;
            return this;
        }

        public HistoricalRainfallBuilder mar(Double mar) {
            this.mar = mar;
            return this;
        }

        public HistoricalRainfallBuilder apr(Double apr) {
            this.apr = apr;
            return this;
        }

        public HistoricalRainfallBuilder may(Double may) {
            this.may = may;
            return this;
        }

        public HistoricalRainfallBuilder jun(Double jun) {
            this.jun = jun;
            return this;
        }

        public HistoricalRainfallBuilder jul(Double jul) {
            this.jul = jul;
            return this;
        }

        public HistoricalRainfallBuilder aug(Double aug) {
            this.aug = aug;
            return this;
        }

        public HistoricalRainfallBuilder sep(Double sep) {
            this.sep = sep;
            return this;
        }

        public HistoricalRainfallBuilder oct(Double oct) {
            this.oct = oct;
            return this;
        }

        public HistoricalRainfallBuilder nov(Double nov) {
            this.nov = nov;
            return this;
        }

        public HistoricalRainfallBuilder dec(Double dec) {
            this.dec = dec;
            return this;
        }

        public HistoricalRainfallBuilder annualMm(Double annualMm) {
            this.annualMm = annualMm;
            return this;
        }

        public HistoricalRainfall build() {
            HistoricalRainfall instance = new HistoricalRainfall();
            instance.setId(this.id);
            instance.setSubdivision(this.subdivision);
            instance.setYear(this.year);
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
