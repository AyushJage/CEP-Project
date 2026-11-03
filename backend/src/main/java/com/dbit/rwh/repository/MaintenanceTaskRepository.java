package com.dbit.rwh.repository;

import com.dbit.rwh.entity.MaintenanceTask;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MaintenanceTaskRepository extends JpaRepository<MaintenanceTask, Long> {
    List<MaintenanceTask> findAllByFarmerOrderByDueDateAsc(User farmer);
    List<MaintenanceTask> findAllByFarmerIdOrderByDueDateAsc(Long farmerId);
}
