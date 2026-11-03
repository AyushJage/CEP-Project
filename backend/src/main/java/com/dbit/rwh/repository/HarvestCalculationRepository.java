package com.dbit.rwh.repository;

import com.dbit.rwh.entity.HarvestCalculation;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HarvestCalculationRepository extends JpaRepository<HarvestCalculation, Long> {
    List<HarvestCalculation> findAllByFarmerOrderByCreatedAtDesc(User farmer);

    @Query("SELECT COALESCE(SUM(h.harvestedLitres), 0) FROM HarvestCalculation h")
    Double getTotalHarvestedLitres();
}
