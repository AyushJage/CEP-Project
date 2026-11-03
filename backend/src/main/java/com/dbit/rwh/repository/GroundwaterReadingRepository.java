package com.dbit.rwh.repository;

import com.dbit.rwh.entity.GroundwaterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface GroundwaterReadingRepository extends JpaRepository<GroundwaterReading, Long> {
    List<GroundwaterReading> findAllByDistrictIgnoreCase(String district);
    List<GroundwaterReading> findAllByDistrictIgnoreCaseAndReadingDateBetweenOrderByReadingDateAsc(String district, LocalDate start, LocalDate end);

    @Query("SELECT g FROM GroundwaterReading g WHERE LOWER(g.district) = LOWER(:district) AND g.latitude IS NOT NULL AND g.longitude IS NOT NULL")
    List<GroundwaterReading> findWellsByDistrict(@Param("district") String district);
}
