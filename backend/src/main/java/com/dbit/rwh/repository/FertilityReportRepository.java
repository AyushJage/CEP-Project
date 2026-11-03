package com.dbit.rwh.repository;

import com.dbit.rwh.entity.FertilityReport;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FertilityReportRepository extends JpaRepository<FertilityReport, Long> {
    List<FertilityReport> findAllByFarmerOrderByCreatedAtDesc(User farmer);
    Optional<FertilityReport> findTopByFarmerOrderByCreatedAtDesc(User farmer);
    Optional<FertilityReport> findTopByFarmerIdOrderByCreatedAtDesc(Long farmerId);
}
