package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "alert")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @Column(nullable = false, length = 50)
    private String type; // LOW_TANK, HEAVY_RAIN, MAINTENANCE_DUE, SYSTEM

    @Column(name = "message_key", nullable = false)
    private String messageKey;

    @Column(name = "message_params")
    private String messageParams;

    @Column(name = "is_read", nullable = false)
    private Boolean isRead = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (isRead == null) isRead = false;
    }

    public Alert() {}

    public Alert(Long id, User farmer, String type, String messageKey, String messageParams, Boolean isRead, LocalDateTime createdAt) {
        this.id = id;
        this.farmer = farmer;
        this.type = type;
        this.messageKey = messageKey;
        this.messageParams = messageParams;
        this.isRead = isRead;
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

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessageKey() {
        return this.messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageParams() {
        return this.messageParams;
    }

    public void setMessageParams(String messageParams) {
        this.messageParams = messageParams;
    }

    public Boolean getIsRead() {
        return this.isRead;
    }

    public Boolean isIsRead() {
        return this.isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static AlertBuilder builder() {
        return new AlertBuilder();
    }

    public static class AlertBuilder {
        private Long id;
        private User farmer;
        private String type;
        private String messageKey;
        private String messageParams;
        private Boolean isRead = false;
        private LocalDateTime createdAt;

        public AlertBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AlertBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public AlertBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AlertBuilder messageKey(String messageKey) {
            this.messageKey = messageKey;
            return this;
        }

        public AlertBuilder messageParams(String messageParams) {
            this.messageParams = messageParams;
            return this;
        }

        public AlertBuilder isRead(Boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        public AlertBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Alert build() {
            Alert instance = new Alert();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setType(this.type);
            instance.setMessageKey(this.messageKey);
            instance.setMessageParams(this.messageParams);
            instance.setIsRead(this.isRead);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
