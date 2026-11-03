package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 15)
    private String phone; // used as username

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role; // FARMER, FIELD_WORKER, ADMIN

    @Column(nullable = false, length = 10)
    private String language = "mr"; // "mr" default, or "en"

    private String village;
    private String taluka;
    private String district;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (language == null) {
            language = "mr";
        }
    }

    public User() {}

    public User(Long id, String name, String phone, String passwordHash, Role role, String language, String village, String taluka, String district, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.passwordHash = passwordHash;
        this.role = role;
        this.language = language;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getVillage() {
        return this.village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getTaluka() {
        return this.taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String name;
        private String phone;
        private String passwordHash;
        private Role role;
        private String language = "mr";
        private String village;
        private String taluka;
        private String district;
        private LocalDateTime createdAt;

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder passwordHash(String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        public UserBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder language(String language) {
            this.language = language;
            return this;
        }

        public UserBuilder village(String village) {
            this.village = village;
            return this;
        }

        public UserBuilder taluka(String taluka) {
            this.taluka = taluka;
            return this;
        }

        public UserBuilder district(String district) {
            this.district = district;
            return this;
        }

        public UserBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public User build() {
            User instance = new User();
            instance.setId(this.id);
            instance.setName(this.name);
            instance.setPhone(this.phone);
            instance.setPasswordHash(this.passwordHash);
            instance.setRole(this.role);
            instance.setLanguage(this.language);
            instance.setVillage(this.village);
            instance.setTaluka(this.taluka);
            instance.setDistrict(this.district);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
