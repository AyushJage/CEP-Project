package com.dbit.rwh.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "farmer_profile")
public class FarmerProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "land_area_sqm")
    private Double landAreaSqm;

    @Column(name = "irrigation_source")
    private String irrigationSource; // Rain-fed / Borewell / Well / Canal / River / Other

    @Column(name = "rwh_structure")
    private String rwhStructure; // None / Farm pond / Storage tank / Check dam / Roof harvesting / Other

    @Column(name = "main_crop")
    private String mainCrop; // soybean, cotton, sugarcane, etc.

    @Column(name = "soil_type")
    private String soilType; // Black (Regur), Red, Laterite, Alluvial, Loamy

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_field_worker_id")
    private User assignedFieldWorker;

    public FarmerProfile() {}

    public FarmerProfile(Long id, User user, Double landAreaSqm, String irrigationSource, String rwhStructure, String mainCrop, String soilType, User assignedFieldWorker) {
        this.id = id;
        this.user = user;
        this.landAreaSqm = landAreaSqm;
        this.irrigationSource = irrigationSource;
        this.rwhStructure = rwhStructure;
        this.mainCrop = mainCrop;
        this.soilType = soilType;
        this.assignedFieldWorker = assignedFieldWorker;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public User getAssignedFieldWorker() {
        return this.assignedFieldWorker;
    }

    public void setAssignedFieldWorker(User assignedFieldWorker) {
        this.assignedFieldWorker = assignedFieldWorker;
    }

    public static FarmerProfileBuilder builder() {
        return new FarmerProfileBuilder();
    }

    public static class FarmerProfileBuilder {
        private Long id;
        private User user;
        private Double landAreaSqm;
        private String irrigationSource;
        private String rwhStructure;
        private String mainCrop;
        private String soilType;
        private User assignedFieldWorker;

        public FarmerProfileBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public FarmerProfileBuilder user(User user) {
            this.user = user;
            return this;
        }

        public FarmerProfileBuilder landAreaSqm(Double landAreaSqm) {
            this.landAreaSqm = landAreaSqm;
            return this;
        }

        public FarmerProfileBuilder irrigationSource(String irrigationSource) {
            this.irrigationSource = irrigationSource;
            return this;
        }

        public FarmerProfileBuilder rwhStructure(String rwhStructure) {
            this.rwhStructure = rwhStructure;
            return this;
        }

        public FarmerProfileBuilder mainCrop(String mainCrop) {
            this.mainCrop = mainCrop;
            return this;
        }

        public FarmerProfileBuilder soilType(String soilType) {
            this.soilType = soilType;
            return this;
        }

        public FarmerProfileBuilder assignedFieldWorker(User assignedFieldWorker) {
            this.assignedFieldWorker = assignedFieldWorker;
            return this;
        }

        public FarmerProfile build() {
            FarmerProfile instance = new FarmerProfile();
            instance.setId(this.id);
            instance.setUser(this.user);
            instance.setLandAreaSqm(this.landAreaSqm);
            instance.setIrrigationSource(this.irrigationSource);
            instance.setRwhStructure(this.rwhStructure);
            instance.setMainCrop(this.mainCrop);
            instance.setSoilType(this.soilType);
            instance.setAssignedFieldWorker(this.assignedFieldWorker);
            return instance;
        }
    }

}
