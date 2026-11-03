package com.dbit.rwh.repository;

import com.dbit.rwh.entity.DailyWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DailyWeatherRepository extends JpaRepository<DailyWeather, Long> {
    List<DailyWeather> findAllByCityIgnoreCaseAndWeatherDateBetweenOrderByWeatherDateAsc(String city, LocalDate start, LocalDate end);

    @Query("SELECT DISTINCT d.city FROM DailyWeather d ORDER BY d.city ASC")
    List<String> findDistinctCities();
}
