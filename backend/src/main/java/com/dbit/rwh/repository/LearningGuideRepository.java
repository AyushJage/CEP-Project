package com.dbit.rwh.repository;

import com.dbit.rwh.entity.LearningGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LearningGuideRepository extends JpaRepository<LearningGuide, Long> {
    List<LearningGuide> findAllByCategory(String category);
    List<LearningGuide> findByTargetSituationContaining(String targetSituation);
}
