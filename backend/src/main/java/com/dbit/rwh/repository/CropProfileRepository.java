package com.dbit.rwh.repository;

import com.dbit.rwh.entity.CropProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CropProfileRepository extends JpaRepository<CropProfile, Long> {
    List<CropProfile> findAllBySeason(String season);
    List<CropProfile> findAllBySuitedRegionsContaining(String region);
}
