package com.dbit.rwh.repository;

import com.dbit.rwh.entity.RainfallRecord;
import com.dbit.rwh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RainfallRecordRepository extends JpaRepository<RainfallRecord, Long> {
    List<RainfallRecord> findAllByFarmerOrderByRecordDateDesc(User farmer);
    List<RainfallRecord> findAllByFarmerAndRecordDateBetweenOrderByRecordDateAsc(User farmer, LocalDate start, LocalDate end);
    List<RainfallRecord> findAllByRecordDateBetween(LocalDate start, LocalDate end);
    
    @Query("SELECT r FROM RainfallRecord r WHERE r.farmer.district = :district AND r.recordDate BETWEEN :start AND :end ORDER BY r.recordDate ASC")
    List<RainfallRecord> findByDistrictAndDateRange(@Param("district") String district, @Param("start") LocalDate start, @Param("end") LocalDate end);
}
