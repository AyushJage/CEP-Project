package com.dbit.rwh.dto;

import com.dbit.rwh.entity.TankTransaction.TxnType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class TankTransactionDto {
    private Long id;
    private Long tankId;

    @NotNull(message = "Transaction type is required (INFLOW/OUTFLOW)")
    private TxnType type;

    @NotBlank(message = "Purpose is required")
    private String purpose;

    @NotNull(message = "Litres is required")
    @Positive(message = "Litres must be positive")
    private Double litres;

    @NotNull(message = "Transaction date is required")
    private LocalDate txnDate;

    private String note;

    public TankTransactionDto() {}

    public TankTransactionDto(Long id, Long tankId, TxnType type, String purpose, Double litres, LocalDate txnDate, String note) {
        this.id = id;
        this.tankId = tankId;
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

    public Long getTankId() {
        return this.tankId;
    }

    public void setTankId(Long tankId) {
        this.tankId = tankId;
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

    public static TankTransactionDtoBuilder builder() {
        return new TankTransactionDtoBuilder();
    }

    public static class TankTransactionDtoBuilder {
        private Long id;
        private Long tankId;
        private TxnType type;
        private String purpose;
        private Double litres;
        private LocalDate txnDate;
        private String note;

        public TankTransactionDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public TankTransactionDtoBuilder tankId(Long tankId) {
            this.tankId = tankId;
            return this;
        }

        public TankTransactionDtoBuilder type(TxnType type) {
            this.type = type;
            return this;
        }

        public TankTransactionDtoBuilder purpose(String purpose) {
            this.purpose = purpose;
            return this;
        }

        public TankTransactionDtoBuilder litres(Double litres) {
            this.litres = litres;
            return this;
        }

        public TankTransactionDtoBuilder txnDate(LocalDate txnDate) {
            this.txnDate = txnDate;
            return this;
        }

        public TankTransactionDtoBuilder note(String note) {
            this.note = note;
            return this;
        }

        public TankTransactionDto build() {
            TankTransactionDto instance = new TankTransactionDto();
            instance.setId(this.id);
            instance.setTankId(this.tankId);
            instance.setType(this.type);
            instance.setPurpose(this.purpose);
            instance.setLitres(this.litres);
            instance.setTxnDate(this.txnDate);
            instance.setNote(this.note);
            return instance;
        }
    }

}
