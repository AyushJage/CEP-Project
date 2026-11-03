package com.dbit.rwh.repository;

import com.dbit.rwh.entity.Tank;
import com.dbit.rwh.entity.TankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TankTransactionRepository extends JpaRepository<TankTransaction, Long> {
    List<TankTransaction> findAllByTankOrderByTxnDateDesc(Tank tank);
    List<TankTransaction> findAllByTankIdOrderByTxnDateDesc(Long tankId);

    @Query("SELECT COALESCE(SUM(t.litres), 0) FROM TankTransaction t WHERE t.tank.id = :tankId AND t.type = 'INFLOW'")
    Double getTotalInflowByTankId(@Param("tankId") Long tankId);

    @Query("SELECT COALESCE(SUM(t.litres), 0) FROM TankTransaction t WHERE t.tank.id = :tankId AND t.type = 'OUTFLOW'")
    Double getTotalOutflowByTankId(@Param("tankId") Long tankId);

    @Query("SELECT t.purpose, SUM(t.litres) FROM TankTransaction t WHERE t.tank.id = :tankId AND t.type = 'OUTFLOW' GROUP BY t.purpose")
    List<Object[]> getUsageBreakdownByTankId(@Param("tankId") Long tankId);
}
