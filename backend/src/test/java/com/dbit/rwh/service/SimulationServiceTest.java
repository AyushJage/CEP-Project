package com.dbit.rwh.service;

import com.dbit.rwh.dto.SimulationRequest;
import com.dbit.rwh.dto.SimulationResponse;
import com.dbit.rwh.entity.DailyWeather;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.DailyWeatherRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationServiceTest {

    @Test
    @DisplayName("Should run 365-day water balance simulation, calculate overflow and shortage days")
    void testDailySimulation() {
        User farmer = User.builder().id(1L).name("Farmer").build();

        List<DailyWeather> mockDays = new ArrayList<>();
        mockDays.add(DailyWeather.builder().city("Mumbai").weatherDate(LocalDate.of(2020, 7, 1)).prcpMm(50.0).build());
        mockDays.add(DailyWeather.builder().city("Mumbai").weatherDate(LocalDate.of(2020, 7, 2)).prcpMm(0.0).build());
        mockDays.add(DailyWeather.builder().city("Mumbai").weatherDate(LocalDate.of(2020, 7, 3)).prcpMm(0.0).build());

        DailyWeatherRepository weatherRepo = (DailyWeatherRepository) Proxy.newProxyInstance(
                DailyWeatherRepository.class.getClassLoader(),
                new Class<?>[]{DailyWeatherRepository.class},
                (proxy, method, args) -> {
                    if (method.getName().startsWith("findAllByCityIgnoreCase")) {
                        return mockDays;
                    }
                    return null;
                }
        );

        SimulationService simulationService = new SimulationService(weatherRepo, null);

        SimulationRequest req = new SimulationRequest();
        req.setCity("Mumbai");
        req.setYear(2020);
        req.setCatchmentAreaSqm(100.0);
        req.setRunoffCoeff(0.85);
        req.setCapacityLitres(3000.0);
        req.setDailyDemandLitres(500.0);

        SimulationResponse res = simulationService.runSimulation(farmer, req);

        assertNotNull(res);
        assertEquals(4250.0, res.getTotalHarvestedLitres(), 0.01);
        assertTrue(res.getTotalOverflowLitres() > 0, "Overflow should be recorded when inflow exceeds tank capacity");
        assertNotNull(res.getVerdict());
        assertNotNull(res.getAlternativeCapacityShortageDays());
    }
}
