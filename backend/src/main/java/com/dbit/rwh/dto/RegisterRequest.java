package com.dbit.rwh.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Phone must be a valid 10-digit Indian mobile number")
    private String phone;

    @NotBlank(message = "Password is required")
    private String password;

    private String village;
    private String taluka;
    private String district;

    private String language = "mr";

    public RegisterRequest() {}

    public RegisterRequest(String name, String phone, String password, String village, String taluka, String district, String language) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
        this.language = language;
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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static RegisterRequestBuilder builder() {
        return new RegisterRequestBuilder();
    }

    public static class RegisterRequestBuilder {
        private String name;
        private String phone;
        private String password;
        private String village;
        private String taluka;
        private String district;
        private String language = "mr";

        public RegisterRequestBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RegisterRequestBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public RegisterRequestBuilder password(String password) {
            this.password = password;
            return this;
        }

        public RegisterRequestBuilder village(String village) {
            this.village = village;
            return this;
        }

        public RegisterRequestBuilder taluka(String taluka) {
            this.taluka = taluka;
            return this;
        }

        public RegisterRequestBuilder district(String district) {
            this.district = district;
            return this;
        }

        public RegisterRequestBuilder language(String language) {
            this.language = language;
            return this;
        }

        public RegisterRequest build() {
            RegisterRequest instance = new RegisterRequest();
            instance.setName(this.name);
            instance.setPhone(this.phone);
            instance.setPassword(this.password);
            instance.setVillage(this.village);
            instance.setTaluka(this.taluka);
            instance.setDistrict(this.district);
            instance.setLanguage(this.language);
            return instance;
        }
    }

}
