package com.dbit.rwh.service;

import com.dbit.rwh.dto.TankDto;
import com.dbit.rwh.dto.TankTransactionDto;
import com.dbit.rwh.entity.Alert;
import com.dbit.rwh.entity.Tank;
import com.dbit.rwh.entity.TankTransaction;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.AlertRepository;
import com.dbit.rwh.repository.TankRepository;
import com.dbit.rwh.repository.TankTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TankService {

    private final TankRepository tankRepo;
    private final TankTransactionRepository txnRepo;
    private final AlertRepository alertRepo;

    public TankDto toDto(Tank tank) {
        double inflow = txnRepo.getTotalInflowByTankId(tank.getId());
        double outflow = txnRepo.getTotalOutflowByTankId(tank.getId());
        double currentLevel = Math.max(0.0, inflow - outflow);
        currentLevel = Math.min(tank.getCapacityLitres(), currentLevel);

        double percentage = (tank.getCapacityLitres() > 0)
                ? (currentLevel / tank.getCapacityLitres()) * 100.0
                : 0.0;
        percentage = Math.round(percentage * 10.0) / 10.0;

        String color = "green";
        if (percentage < 20.0) color = "red";
        else if (percentage <= 50.0) color = "amber";

        return TankDto.builder()
                .id(tank.getId())
                .name(tank.getName())
                .type(tank.getType())
                .capacityLitres(tank.getCapacityLitres())
                .currentLevelLitres(Math.round(currentLevel * 10.0) / 10.0)
                .levelPercentage(percentage)
                .statusColor(color)
                .build();
    }

    public List<TankDto> getFarmerTanks(User farmer) {
        return tankRepo.findAllByFarmer(farmer).stream().map(this::toDto).toList();
    }

    public TankDto getTankById(Long id, User farmer) {
        Tank tank = tankRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tank not found with ID: " + id));
        if (!tank.getFarmer().getId().equals(farmer.getId())) {
            throw new IllegalArgumentException("Unauthorized to view this tank");
        }
        return toDto(tank);
    }

    @Transactional
    public TankDto createTank(User farmer, TankDto dto) {
        Tank tank = Tank.builder()
                .farmer(farmer)
                .name(dto.getName())
                .type(dto.getType())
                .capacityLitres(dto.getCapacityLitres())
                .build();
        tankRepo.save(tank);
        return toDto(tank);
    }

    @Transactional
    public void deleteTank(Long tankId, User farmer) {
        Tank tank = tankRepo.findById(tankId)
                .orElseThrow(() -> new IllegalArgumentException("Tank not found"));
        if (!tank.getFarmer().getId().equals(farmer.getId())) {
            throw new IllegalArgumentException("Unauthorized to delete this tank");
        }
        tankRepo.delete(tank);
    }

    @Transactional
    public TankTransactionDto addTransaction(Long tankId, User farmer, TankTransactionDto dto) {
        Tank tank = tankRepo.findById(tankId)
                .orElseThrow(() -> new IllegalArgumentException("Tank not found with ID: " + tankId));
        if (!tank.getFarmer().getId().equals(farmer.getId())) {
            throw new IllegalArgumentException("Unauthorized to record transaction for this tank");
        }

        double inflow = txnRepo.getTotalInflowByTankId(tank.getId());
        double outflow = txnRepo.getTotalOutflowByTankId(tank.getId());
        double currentLevel = Math.max(0.0, inflow - outflow);

        if (dto.getType() == TankTransaction.TxnType.OUTFLOW) {
            if (dto.getLitres() > currentLevel) {
                throw new IllegalArgumentException("Insufficient water in tank! Current level: " + currentLevel + " L, Requested: " + dto.getLitres() + " L");
            }
        }

        TankTransaction txn = TankTransaction.builder()
                .tank(tank)
                .type(dto.getType())
                .purpose(dto.getPurpose())
                .litres(dto.getLitres())
                .txnDate(dto.getTxnDate())
                .note(dto.getNote())
                .build();
        txnRepo.save(txn);

        // Check if new level is below 20% to auto-generate Alert
        double newLevel = (dto.getType() == TankTransaction.TxnType.INFLOW)
                ? (currentLevel + dto.getLitres())
                : (currentLevel - dto.getLitres());
        double pct = (newLevel / tank.getCapacityLitres()) * 100.0;

        if (pct < 20.0) {
            Alert alert = Alert.builder()
                    .farmer(farmer)
                    .type("LOW_TANK")
                    .messageKey("alert.low_tank")
                    .messageParams(tank.getName() + " (" + Math.round(pct) + "%)")
                    .isRead(false)
                    .build();
            alertRepo.save(alert);
        }

        return TankTransactionDto.builder()
                .id(txn.getId())
                .tankId(tank.getId())
                .type(txn.getType())
                .purpose(txn.getPurpose())
                .litres(txn.getLitres())
                .txnDate(txn.getTxnDate())
                .note(txn.getNote())
                .build();
    }

    public List<TankTransactionDto> getTransactions(Long tankId, User farmer) {
        Tank tank = tankRepo.findById(tankId)
                .orElseThrow(() -> new IllegalArgumentException("Tank not found"));
        if (!tank.getFarmer().getId().equals(farmer.getId())) {
            throw new IllegalArgumentException("Unauthorized to view transactions");
        }

        return txnRepo.findAllByTankOrderByTxnDateDesc(tank).stream()
                .map(t -> TankTransactionDto.builder()
                        .id(t.getId())
                        .tankId(tankId)
                        .type(t.getType())
                        .purpose(t.getPurpose())
                        .litres(t.getLitres())
                        .txnDate(t.getTxnDate())
                        .note(t.getNote())
                        .build())
                .toList();
    }

    public Map<String, Double> getUsageBreakdown(Long tankId, User farmer) {
        Tank tank = tankRepo.findById(tankId)
                .orElseThrow(() -> new IllegalArgumentException("Tank not found"));
        if (!tank.getFarmer().getId().equals(farmer.getId())) {
            throw new IllegalArgumentException("Unauthorized");
        }

        List<Object[]> rows = txnRepo.getUsageBreakdownByTankId(tankId);
        Map<String, Double> map = new LinkedHashMap<>();
        for (Object[] r : rows) {
            String purpose = (String) r[0];
            Double sum = (Double) r[1];
            map.put(purpose, sum);
        }
        return map;
    }

    public TankService(TankRepository tankRepo, TankTransactionRepository txnRepo, AlertRepository alertRepo) {
        this.tankRepo = tankRepo;
        this.txnRepo = txnRepo;
        this.alertRepo = alertRepo;
    }

}
