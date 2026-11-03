package com.dbit.rwh.service;

import com.dbit.rwh.dto.AlertDto;
import com.dbit.rwh.dto.MaintenanceTaskDto;
import com.dbit.rwh.entity.Alert;
import com.dbit.rwh.entity.MaintenanceTask;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.AlertRepository;
import com.dbit.rwh.repository.MaintenanceTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlertService {

    private final AlertRepository alertRepo;
    private final MaintenanceTaskRepository maintenanceRepo;

    public List<AlertDto> getFarmerAlerts(User farmer) {
        return alertRepo.findAllByFarmerOrderByCreatedAtDesc(farmer)
                .stream()
                .map(a -> AlertDto.builder()
                        .id(a.getId())
                        .type(a.getType())
                        .messageKey(a.getMessageKey())
                        .messageParams(a.getMessageParams())
                        .isRead(a.getIsRead())
                        .createdAt(a.getCreatedAt())
                        .build())
                .toList();
    }

    public long getUnreadCount(User farmer) {
        return alertRepo.countByFarmerAndIsReadFalse(farmer);
    }

    @Transactional
    public void markAlertRead(Long alertId, User farmer) {
        Alert alert = alertRepo.findById(alertId)
                .orElseThrow(() -> new IllegalArgumentException("Alert not found"));
        if (!alert.getFarmer().getId().equals(farmer.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        alert.setIsRead(true);
        alertRepo.save(alert);
    }

    public List<MaintenanceTaskDto> getMaintenanceTasks(User farmer) {
        return maintenanceRepo.findAllByFarmerOrderByDueDateAsc(farmer)
                .stream()
                .map(m -> MaintenanceTaskDto.builder()
                        .id(m.getId())
                        .tankId(m.getTank() != null ? m.getTank().getId() : null)
                        .tankName(m.getTank() != null ? m.getTank().getName() : "-")
                        .titleKey(m.getTitleKey())
                        .dueDate(m.getDueDate())
                        .status(m.getStatus())
                        .build())
                .toList();
    }

    @Transactional
    public void markTaskDone(Long taskId, User farmer) {
        MaintenanceTask task = maintenanceRepo.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        if (!task.getFarmer().getId().equals(farmer.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }
        task.setStatus(MaintenanceTask.Status.DONE);
        maintenanceRepo.save(task);
    }

    public AlertService(AlertRepository alertRepo, MaintenanceTaskRepository maintenanceRepo) {
        this.alertRepo = alertRepo;
        this.maintenanceRepo = maintenanceRepo;
    }

}
