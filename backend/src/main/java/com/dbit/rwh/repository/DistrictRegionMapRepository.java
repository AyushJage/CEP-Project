package com.dbit.rwh.repository;

import com.dbit.rwh.entity.DistrictRegionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DistrictRegionMapRepository extends JpaRepository<DistrictRegionMap, String> {
    Optional<DistrictRegionMap> findByDistrictIgnoreCase(String district);
}
