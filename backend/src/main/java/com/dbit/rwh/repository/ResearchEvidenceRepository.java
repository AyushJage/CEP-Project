package com.dbit.rwh.repository;

import com.dbit.rwh.entity.ResearchEvidence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResearchEvidenceRepository extends JpaRepository<ResearchEvidence, Long> {
    List<ResearchEvidence> findAllByCategory(String category);
}
