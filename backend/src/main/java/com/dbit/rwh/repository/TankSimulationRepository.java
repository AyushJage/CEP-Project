package com.dbit.rwh.repository;

import com.dbit.rwh.entity.TankSimulation;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TankSimulationRepository extends JpaRepository<TankSimulation, Long> {
    List<TankSimulation> findAllByFarmerOrderByCreatedAtDesc(User farmer);
    List<TankSimulation> findAllByFarmerIdOrderByCreatedAtDesc(Long farmerId);
}
