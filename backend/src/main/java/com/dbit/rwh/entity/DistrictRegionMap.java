package com.dbit.rwh.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "district_region_map")
public class DistrictRegionMap {

    @Id
    @Column(length = 100)
    private String district;

    @Column(nullable = false, length = 100)
    private String region; // Konkan, Western Maharashtra, Khandesh / North Maharashtra, Marathwada, Vidarbha

    @Column(name = "rainfall_subdivision", nullable = false, length = 100)
    private String rainfallSubdivision; // KONKAN & GOA, MADHYA MAHARASHTRA, MATATHWADA, VIDARBHA

    public DistrictRegionMap() {}

    public DistrictRegionMap(String district, String region, String rainfallSubdivision) {
        this.district = district;
        this.region = region;
        this.rainfallSubdivision = rainfallSubdivision;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRainfallSubdivision() {
        return this.rainfallSubdivision;
    }

    public void setRainfallSubdivision(String rainfallSubdivision) {
        this.rainfallSubdivision = rainfallSubdivision;
    }

    public static DistrictRegionMapBuilder builder() {
        return new DistrictRegionMapBuilder();
    }

    public static class DistrictRegionMapBuilder {
        private String district;
        private String region;
        private String rainfallSubdivision;

        public DistrictRegionMapBuilder district(String district) {
            this.district = district;
            return this;
        }

        public DistrictRegionMapBuilder region(String region) {
            this.region = region;
            return this;
        }

        public DistrictRegionMapBuilder rainfallSubdivision(String rainfallSubdivision) {
            this.rainfallSubdivision = rainfallSubdivision;
            return this;
        }

        public DistrictRegionMap build() {
            DistrictRegionMap instance = new DistrictRegionMap();
            instance.setDistrict(this.district);
            instance.setRegion(this.region);
            instance.setRainfallSubdivision(this.rainfallSubdivision);
            return instance;
        }
    }

}
