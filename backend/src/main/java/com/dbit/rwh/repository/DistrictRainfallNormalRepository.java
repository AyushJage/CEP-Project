package com.dbit.rwh.repository;

import com.dbit.rwh.entity.DistrictRainfallNormal;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DistrictRainfallNormalRepository extends JpaRepository<DistrictRainfallNormal, Long> {
    Optional<DistrictRainfallNormal> findByDistrictIgnoreCase(String district);
}
