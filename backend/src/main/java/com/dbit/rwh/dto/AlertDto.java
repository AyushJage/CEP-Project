package com.dbit.rwh.dto;

import java.time.LocalDateTime;

public class AlertDto {
    private Long id;
    private String type;
    private String messageKey;
    private String messageParams;
    private Boolean isRead;
    private LocalDateTime createdAt;

    public AlertDto() {}

    public AlertDto(Long id, String type, String messageKey, String messageParams, Boolean isRead, LocalDateTime createdAt) {
        this.id = id;
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

    public static AlertDtoBuilder builder() {
        return new AlertDtoBuilder();
    }

    public static class AlertDtoBuilder {
        private Long id;
        private String type;
        private String messageKey;
        private String messageParams;
        private Boolean isRead;
        private LocalDateTime createdAt;

        public AlertDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AlertDtoBuilder type(String type) {
            this.type = type;
            return this;
        }

        public AlertDtoBuilder messageKey(String messageKey) {
            this.messageKey = messageKey;
            return this;
        }

        public AlertDtoBuilder messageParams(String messageParams) {
            this.messageParams = messageParams;
            return this;
        }

        public AlertDtoBuilder isRead(Boolean isRead) {
            this.isRead = isRead;
            return this;
        }

        public AlertDtoBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AlertDto build() {
            AlertDto instance = new AlertDto();
            instance.setId(this.id);
            instance.setType(this.type);
            instance.setMessageKey(this.messageKey);
            instance.setMessageParams(this.messageParams);
            instance.setIsRead(this.isRead);
            instance.setCreatedAt(this.createdAt);
            return instance;
        }
    }

}
