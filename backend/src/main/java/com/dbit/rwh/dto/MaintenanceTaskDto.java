package com.dbit.rwh.dto;

import com.dbit.rwh.entity.MaintenanceTask.Status;
import java.time.LocalDate;

public class MaintenanceTaskDto {
    private Long id;
    private Long tankId;
    private String tankName;
    private String titleKey;
    private LocalDate dueDate;
    private Status status;

    public MaintenanceTaskDto() {}

    public MaintenanceTaskDto(Long id, Long tankId, String tankName, String titleKey, LocalDate dueDate, Status status) {
        this.id = id;
        this.tankId = tankId;
        this.tankName = tankName;
        this.titleKey = titleKey;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTankId() {
        return this.tankId;
    }

    public void setTankId(Long tankId) {
        this.tankId = tankId;
    }

    public String getTankName() {
        return this.tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public String getTitleKey() {
        return this.titleKey;
    }

    public void setTitleKey(String titleKey) {
        this.titleKey = titleKey;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static MaintenanceTaskDtoBuilder builder() {
        return new MaintenanceTaskDtoBuilder();
    }

    public static class MaintenanceTaskDtoBuilder {
        private Long id;
        private Long tankId;
        private String tankName;
        private String titleKey;
        private LocalDate dueDate;
        private Status status;

        public MaintenanceTaskDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MaintenanceTaskDtoBuilder tankId(Long tankId) {
            this.tankId = tankId;
            return this;
        }

        public MaintenanceTaskDtoBuilder tankName(String tankName) {
            this.tankName = tankName;
            return this;
        }

        public MaintenanceTaskDtoBuilder titleKey(String titleKey) {
            this.titleKey = titleKey;
            return this;
        }

        public MaintenanceTaskDtoBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public MaintenanceTaskDtoBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public MaintenanceTaskDto build() {
            MaintenanceTaskDto instance = new MaintenanceTaskDto();
            instance.setId(this.id);
            instance.setTankId(this.tankId);
            instance.setTankName(this.tankName);
            instance.setTitleKey(this.titleKey);
            instance.setDueDate(this.dueDate);
            instance.setStatus(this.status);
            return instance;
        }
    }

}
