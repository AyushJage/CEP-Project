package com.dbit.rwh.repository;

import com.dbit.rwh.entity.AiPrediction;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AiPredictionRepository extends JpaRepository<AiPrediction, Long> {
    List<AiPrediction> findAllByFarmerOrderByCreatedAtDesc(User farmer);
    List<AiPrediction> findAllByFarmerIdOrderByCreatedAtDesc(Long farmerId);
}
