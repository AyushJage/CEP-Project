package com.dbit.rwh.repository;

import com.dbit.rwh.entity.Alert;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllByFarmerOrderByCreatedAtDesc(User farmer);
    List<Alert> findAllByFarmerIdOrderByCreatedAtDesc(Long farmerId);
    long countByFarmerAndIsReadFalse(User farmer);
}
