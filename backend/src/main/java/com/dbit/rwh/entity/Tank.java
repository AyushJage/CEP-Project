package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tank")
public class Tank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type; // Farm pond (Shet-tale), Masonry tank, Plastic syntax tank, Jalkund, Recharge pit

    @Column(name = "capacity_litres", nullable = false)
    private Double capacityLitres;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public Tank() {}

    public Tank(Long id, User farmer, String name, String type, Double capacityLitres, LocalDateTime createdAt) {
        this.id = id;
        this.farmer = farmer;
        this.name = name;
        this.type = type;
        this.capacityLitres = capacityLitres;
        this.createdAt = createdAt;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getCapacityLitres() {
        return this.capacityLitres;
    }

    public void setCapacityLitres(Double capacityLitres) {
        this.capacityLitres = capacityLitres;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static TankBuilder builder() {
        return new TankBuilder();
    }

    public static class TankBuilder {
        private Long id;
        private User farmer;
        private String name;
        private String type;
        private Double capacityLitres;
        private LocalDateTime createdAt;

        public TankBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TankBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public TankBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TankBuilder type(String type) {
            this.type = type;
            return this;
        }

        public TankBuilder capacityLitres(Double capacityLitres) {
            this.capacityLitres = capacityLitres;
            return this;
        }

        public TankBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Tank build() {
            Tank instance = new Tank();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setName(this.name);
            instance.setType(this.type);
            instance.setCapacityLitres(this.capacityLitres);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
