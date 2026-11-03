package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tank_transaction")
public class TankTransaction {

    public enum TxnType {
        INFLOW,
        OUTFLOW
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tank_id", nullable = false)
    private Tank tank;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private TxnType type; // INFLOW, OUTFLOW

    @Column(nullable = false)
    private String purpose; // Rain harvest, Well transfer, Irrigation, Drinking, Livestock, Domestic, Other

    @Column(nullable = false)
    private Double litres;

    @Column(name = "txn_date", nullable = false)
    private LocalDate txnDate;

    @Column(columnDefinition = "TEXT")
    private String note;

    public TankTransaction() {}

    public TankTransaction(Long id, Tank tank, TxnType type, String purpose, Double litres, LocalDate txnDate, String note) {
        this.id = id;
        this.tank = tank;
        this.type = type;
        this.purpose = purpose;
        this.litres = litres;
        this.txnDate = txnDate;
        this.note = note;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tank getTank() {
        return this.tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public TxnType getType() {
        return this.type;
    }

    public void setType(TxnType type) {
        this.type = type;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Double getLitres() {
        return this.litres;
    }

    public void setLitres(Double litres) {
        this.litres = litres;
    }

    public LocalDate getTxnDate() {
        return this.txnDate;
    }

    public void setTxnDate(LocalDate txnDate) {
        this.txnDate = txnDate;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public static TankTransactionBuilder builder() {
        return new TankTransactionBuilder();
    }

    public static class TankTransactionBuilder {
        private Long id;
        private Tank tank;
        private TxnType type;
        private String purpose;
        private Double litres;
        private LocalDate txnDate;
        private String note;

        public TankTransactionBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TankTransactionBuilder tank(Tank tank) {
            this.tank = tank;
            return this;
        }

        public TankTransactionBuilder type(TxnType type) {
            this.type = type;
            return this;
        }

        public TankTransactionBuilder purpose(String purpose) {
            this.purpose = purpose;
            return this;
        }

        public TankTransactionBuilder litres(Double litres) {
            this.litres = litres;
            return this;
        }

        public TankTransactionBuilder txnDate(LocalDate txnDate) {
            this.txnDate = txnDate;
            return this;
        }

        public TankTransactionBuilder note(String note) {
            this.note = note;
            return this;
        }

        public TankTransaction build() {
            TankTransaction instance = new TankTransaction();
            instance.setId(this.id);
            instance.setTank(this.tank);
            instance.setType(this.type);
            instance.setPurpose(this.purpose);
            instance.setLitres(this.litres);
            instance.setTxnDate(this.txnDate);
            instance.setNote(this.note);
            return instance;
        }
    }

}
