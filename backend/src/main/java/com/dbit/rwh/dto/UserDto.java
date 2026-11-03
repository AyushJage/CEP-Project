package com.dbit.rwh.dto;

import com.dbit.rwh.entity.Role;

public class UserDto {
    private Long id;
    private String name;
    private String phone;
    private Role role;
    private String language;
    private String village;
    private String taluka;
    private String district;

    public UserDto() {}

    public UserDto(Long id, String name, String phone, Role role, String language, String village, String taluka, String district) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.role = role;
        this.language = language;
        this.village = village;
        this.taluka = taluka;
        this.district = district;
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

    public static UserDtoBuilder builder() {
        return new UserDtoBuilder();
    }

    public static class UserDtoBuilder {
        private Long id;
        private String name;
        private String phone;
        private Role role;
        private String language;
        private String village;
        private String taluka;
        private String district;

        public UserDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserDtoBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserDtoBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserDtoBuilder language(String language) {
            this.language = language;
            return this;
        }

        public UserDtoBuilder village(String village) {
            this.village = village;
            return this;
        }

        public UserDtoBuilder taluka(String taluka) {
            this.taluka = taluka;
            return this;
        }

        public UserDtoBuilder district(String district) {
            this.district = district;
            return this;
        }

        public UserDto build() {
            UserDto instance = new UserDto();
            instance.setId(this.id);
            instance.setName(this.name);
            instance.setPhone(this.phone);
            instance.setRole(this.role);
            instance.setLanguage(this.language);
            instance.setVillage(this.village);
            instance.setTaluka(this.taluka);
            instance.setDistrict(this.district);
            return instance;
        }
    }

}
