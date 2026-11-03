package com.dbit.rwh.repository;

import com.dbit.rwh.entity.Recommendation;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findAllByFarmerOrderByCreatedAtDesc(User farmer);
    Optional<Recommendation> findTopByFarmerOrderByCreatedAtDesc(User farmer);
    Optional<Recommendation> findTopByFarmerIdOrderByCreatedAtDesc(Long farmerId);
}
