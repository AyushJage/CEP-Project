package com.dbit.rwh.repository;

import com.dbit.rwh.entity.FarmerProfile;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FarmerProfileRepository extends JpaRepository<FarmerProfile, Long> {
    Optional<FarmerProfile> findByUser(User user);
    Optional<FarmerProfile> findByUserId(Long userId);
    List<FarmerProfile> findAllByAssignedFieldWorker(User fieldWorker);
}
