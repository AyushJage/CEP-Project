package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "rainfall_record")
public class RainfallRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(name = "rainfall_mm", nullable = false)
    private Double rainfallMm;

    private String district;
    private String taluka;

    @Column(columnDefinition = "TEXT")
    private String note;

    public RainfallRecord() {}

    public RainfallRecord(Long id, User farmer, LocalDate recordDate, Double rainfallMm, String district, String taluka, String note) {
        this.id = id;
        this.farmer = farmer;
        this.recordDate = recordDate;
        this.rainfallMm = rainfallMm;
        this.district = district;
        this.taluka = taluka;
        this.note = note;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFarmer() {
        return this.farmer;
    }

    public void setFarmer(User farmer) {
        this.farmer = farmer;
    }

    public LocalDate getRecordDate() {
        return this.recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public Double getRainfallMm() {
        return this.rainfallMm;
    }

    public void setRainfallMm(Double rainfallMm) {
        this.rainfallMm = rainfallMm;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTaluka() {
        return this.taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static RainfallRecordBuilder builder() {
        return new RainfallRecordBuilder();
    }

    public static class RainfallRecordBuilder {
        private Long id;
        private User farmer;
        private LocalDate recordDate;
        private Double rainfallMm;
        private String district;
        private String taluka;
        private String note;

        public RainfallRecordBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public RainfallRecordBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public RainfallRecordBuilder recordDate(LocalDate recordDate) {
            this.recordDate = recordDate;
            return this;
        }

        public RainfallRecordBuilder rainfallMm(Double rainfallMm) {
            this.rainfallMm = rainfallMm;
            return this;
        }

        public RainfallRecordBuilder district(String district) {
            this.district = district;
            return this;
        }

        public RainfallRecordBuilder taluka(String taluka) {
            this.taluka = taluka;
            return this;
        }

        public RainfallRecordBuilder note(String note) {
            this.note = note;
            return this;
        }

        public RainfallRecord build() {
            RainfallRecord instance = new RainfallRecord();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setRecordDate(this.recordDate);
            instance.setRainfallMm(this.rainfallMm);
            instance.setDistrict(this.district);
            instance.setTaluka(this.taluka);
            instance.setNote(this.note);
            return instance;
        }
    }

}
