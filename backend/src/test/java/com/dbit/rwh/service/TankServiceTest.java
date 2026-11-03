package com.dbit.rwh.service;

import com.dbit.rwh.dto.TankDto;
import com.dbit.rwh.entity.Tank;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.TankTransactionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

import static org.junit.jupiter.api.Assertions.*;

class TankServiceTest {

    private double mockInflow = 0.0;
    private double mockOutflow = 0.0;

    private TankTransactionRepository createProxyRepo() {
        return (TankTransactionRepository) Proxy.newProxyInstance(
                TankTransactionRepository.class.getClassLoader(),
                new Class<?>[]{TankTransactionRepository.class},
                (proxy, method, args) -> {
                    if ("getTotalInflowByTankId".equals(method.getName())) {
                        return mockInflow;
                    }
                    if ("getTotalOutflowByTankId".equals(method.getName())) {
                        return mockOutflow;
                    }
                    return 0.0;
                }
        );
    }

    @Test
    @DisplayName("Should accurately calculate current tank level: Inflow minus Outflow")
    void testTankLevelCalculation() {
        mockInflow = 60000.0;
        mockOutflow = 20000.0;
        TankService tankService = new TankService(null, createProxyRepo(), null);

        User farmer = User.builder().id(1L).name("Test Farmer").build();
        Tank tank = Tank.builder()
                .id(10L)
                .name("Main Storage Pond")
                .capacityLitres(100000.0)
                .farmer(farmer)
                .build();

        TankDto dto = tankService.toDto(tank);

        assertNotNull(dto);
        assertEquals(40000.0, dto.getCurrentLevelLitres());
        assertEquals(40.0, dto.getLevelPercentage());
        assertEquals("amber", dto.getStatusColor()); // 20-50% is amber
    }

    @Test
    @DisplayName("Should flag status as red when tank level falls below 20%")
    void testLowTankLevelUnderTwentyPercent() {
        mockInflow = 1500.0;
        mockOutflow = 0.0;
        TankService tankService = new TankService(null, createProxyRepo(), null);

        User farmer = User.builder().id(1L).name("Test Farmer").build();
        Tank tank = Tank.builder()
                .id(11L)
                .name("Rooftop Tank")
                .capacityLitres(10000.0)
                .farmer(farmer)
                .build();

        TankDto dto = tankService.toDto(tank);

        assertEquals(1500.0, dto.getCurrentLevelLitres());
        assertEquals(15.0, dto.getLevelPercentage());
        assertEquals("red", dto.getStatusColor());
    }

    @Test
    @DisplayName("Should prevent level from exceeding capacity or falling below zero")
    void testBoundsConstraints() {
        User farmer = User.builder().id(1L).name("Test Farmer").build();
        Tank tank = Tank.builder()
                .id(12L)
                .name("Small Cistern")
                .capacityLitres(5000.0)
                .farmer(farmer)
                .build();

        // Outflow > Inflow -> clamped to 0
        mockInflow = 1000.0;
        mockOutflow = 3000.0;
        TankService tankService = new TankService(null, createProxyRepo(), null);
        TankDto dto = tankService.toDto(tank);
        assertEquals(0.0, dto.getCurrentLevelLitres());

        // Inflow > capacity -> clamped to 5000
        mockInflow = 8000.0;
        mockOutflow = 0.0;
        TankDto dtoOver = tankService.toDto(tank);
        assertEquals(5000.0, dtoOver.getCurrentLevelLitres());
        assertEquals(100.0, dtoOver.getLevelPercentage());
        assertEquals("green", dtoOver.getStatusColor());
    }
}
