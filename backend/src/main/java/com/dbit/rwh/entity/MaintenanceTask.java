package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance_task")
public class MaintenanceTask {

    public enum Status {
        PENDING,
        DONE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tank_id")
    private Tank tank;

    @Column(name = "title_key", nullable = false)
    private String titleKey;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 15)
    private Status status = Status.PENDING;

    @PrePersist
    protected void onCreate() {
        if (status == null) status = Status.PENDING;
    }

    public MaintenanceTask() {}

    public MaintenanceTask(Long id, User farmer, Tank tank, String titleKey, LocalDate dueDate, Status status) {
        this.id = id;
        this.farmer = farmer;
        this.tank = tank;
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

    public User getFarmer() {
        return this.farmer;
    }

    public void setFarmer(User farmer) {
        this.farmer = farmer;
    }

    public Tank getTank() {
        return this.tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
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

    public static MaintenanceTaskBuilder builder() {
        return new MaintenanceTaskBuilder();
    }

    public static class MaintenanceTaskBuilder {
        private Long id;
        private User farmer;
        private Tank tank;
        private String titleKey;
        private LocalDate dueDate;
        private Status status = Status.PENDING;

        public MaintenanceTaskBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MaintenanceTaskBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public MaintenanceTaskBuilder tank(Tank tank) {
            this.tank = tank;
            return this;
        }

        public MaintenanceTaskBuilder titleKey(String titleKey) {
            this.titleKey = titleKey;
            return this;
        }

        public MaintenanceTaskBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public MaintenanceTaskBuilder status(Status status) {
            this.status = status;
            return this;
        }

        public MaintenanceTask build() {
            MaintenanceTask instance = new MaintenanceTask();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setTank(this.tank);
            instance.setTitleKey(this.titleKey);
            instance.setDueDate(this.dueDate);
            instance.setStatus(this.status);
            return instance;
        }
    }

}
