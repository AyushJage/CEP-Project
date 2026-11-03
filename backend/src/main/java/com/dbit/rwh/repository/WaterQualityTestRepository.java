package com.dbit.rwh.repository;

import com.dbit.rwh.entity.User;
import com.dbit.rwh.entity.WaterQualityTest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WaterQualityTestRepository extends JpaRepository<WaterQualityTest, Long> {
    List<WaterQualityTest> findAllByFarmerOrderByTestDateDesc(User farmer);
    List<WaterQualityTest> findAllByFarmerIdOrderByTestDateDesc(Long farmerId);
}
