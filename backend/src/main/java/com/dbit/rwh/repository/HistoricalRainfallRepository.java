package com.dbit.rwh.repository;

import com.dbit.rwh.entity.HistoricalRainfall;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HistoricalRainfallRepository extends JpaRepository<HistoricalRainfall, Long> {
    List<HistoricalRainfall> findAllBySubdivisionOrderByYearAsc(String subdivision);
}
