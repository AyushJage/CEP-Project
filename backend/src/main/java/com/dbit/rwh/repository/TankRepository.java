package com.dbit.rwh.repository;

import com.dbit.rwh.entity.Tank;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TankRepository extends JpaRepository<Tank, Long> {
    List<Tank> findAllByFarmer(User farmer);
    List<Tank> findAllByFarmerId(Long farmerId);
}
