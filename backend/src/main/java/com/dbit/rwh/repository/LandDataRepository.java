package com.dbit.rwh.repository;

import com.dbit.rwh.entity.LandData;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LandDataRepository extends JpaRepository<LandData, Long> {
    List<LandData> findAllByFarmer(User farmer);
    List<LandData> findAllByFarmerId(Long farmerId);
}
