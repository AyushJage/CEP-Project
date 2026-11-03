package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "water_quality_test")
public class WaterQualityTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farmer_id", nullable = false)
    private User farmer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tank_id")
    private Tank tank;

    @Column(name = "test_date", nullable = false)
    private LocalDate testDate;

    private Double ph;
    
    @Column(name = "tds_or_conductivity")
    private Double tdsOrConductivity;

    private Double turbidity;
    private Double nitrate;

    @Column(name = "coliform_present")
    private Boolean coliformPresent;

    @Column(name = "verdict_drinking", nullable = false, length = 20)
    private String verdictDrinking; // Safe, Caution, Not safe

    @Column(name = "verdict_irrigation", nullable = false, length = 20)
    private String verdictIrrigation; // Safe, Caution, Not safe

    @Column(name = "advice_json", columnDefinition = "TEXT")
    private String adviceJson;

    public WaterQualityTest() {}

    public WaterQualityTest(Long id, User farmer, Tank tank, LocalDate testDate, Double ph, Double tdsOrConductivity, Double turbidity, Double nitrate, Boolean coliformPresent, String verdictDrinking, String verdictIrrigation, String adviceJson) {
        this.id = id;
        this.farmer = farmer;
        this.tank = tank;
        this.testDate = testDate;
        this.ph = ph;
        this.tdsOrConductivity = tdsOrConductivity;
        this.turbidity = turbidity;
        this.nitrate = nitrate;
        this.coliformPresent = coliformPresent;
        this.verdictDrinking = verdictDrinking;
        this.verdictIrrigation = verdictIrrigation;
        this.adviceJson = adviceJson;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFarmer() {
        return this.farmer;
    }

    public void setFarmer(User farmer) {
        this.farmer = farmer;
    }

    public Tank getTank() {
        return this.tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public LocalDate getTestDate() {
        return this.testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public Double getPh() {
        return this.ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getTdsOrConductivity() {
        return this.tdsOrConductivity;
    }

    public void setTdsOrConductivity(Double tdsOrConductivity) {
        this.tdsOrConductivity = tdsOrConductivity;
    }

    public Double getTurbidity() {
        return this.turbidity;
    }

    public void setTurbidity(Double turbidity) {
        this.turbidity = turbidity;
    }

    public Double getNitrate() {
        return this.nitrate;
    }

    public void setNitrate(Double nitrate) {
        this.nitrate = nitrate;
    }

    public Boolean getColiformPresent() {
        return this.coliformPresent;
    }

    public Boolean isColiformPresent() {
        return this.coliformPresent;
    }

    public void setColiformPresent(Boolean coliformPresent) {
        this.coliformPresent = coliformPresent;
    }

    public String getVerdictDrinking() {
        return this.verdictDrinking;
    }

    public void setVerdictDrinking(String verdictDrinking) {
        this.verdictDrinking = verdictDrinking;
    }

    public String getVerdictIrrigation() {
        return this.verdictIrrigation;
    }

    public void setVerdictIrrigation(String verdictIrrigation) {
        this.verdictIrrigation = verdictIrrigation;
    }

    public String getAdviceJson() {
        return this.adviceJson;
    }

    public void setAdviceJson(String adviceJson) {
        this.adviceJson = adviceJson;
    }

    public static WaterQualityTestBuilder builder() {
        return new WaterQualityTestBuilder();
    }

    public static class WaterQualityTestBuilder {
        private Long id;
        private User farmer;
        private Tank tank;
        private LocalDate testDate;
        private Double ph;
        private Double tdsOrConductivity;
        private Double turbidity;
        private Double nitrate;
        private Boolean coliformPresent;
        private String verdictDrinking;
        private String verdictIrrigation;
        private String adviceJson;

        public WaterQualityTestBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public WaterQualityTestBuilder farmer(User farmer) {
            this.farmer = farmer;
            return this;
        }

        public WaterQualityTestBuilder tank(Tank tank) {
            this.tank = tank;
            return this;
        }

        public WaterQualityTestBuilder testDate(LocalDate testDate) {
            this.testDate = testDate;
            return this;
        }

        public WaterQualityTestBuilder ph(Double ph) {
            this.ph = ph;
            return this;
        }

        public WaterQualityTestBuilder tdsOrConductivity(Double tdsOrConductivity) {
            this.tdsOrConductivity = tdsOrConductivity;
            return this;
        }

        public WaterQualityTestBuilder turbidity(Double turbidity) {
            this.turbidity = turbidity;
            return this;
        }

        public WaterQualityTestBuilder nitrate(Double nitrate) {
            this.nitrate = nitrate;
            return this;
        }

        public WaterQualityTestBuilder coliformPresent(Boolean coliformPresent) {
            this.coliformPresent = coliformPresent;
            return this;
        }

        public WaterQualityTestBuilder verdictDrinking(String verdictDrinking) {
            this.verdictDrinking = verdictDrinking;
            return this;
        }

        public WaterQualityTestBuilder verdictIrrigation(String verdictIrrigation) {
            this.verdictIrrigation = verdictIrrigation;
            return this;
        }

        public WaterQualityTestBuilder adviceJson(String adviceJson) {
            this.adviceJson = adviceJson;
            return this;
        }

        public WaterQualityTest build() {
            WaterQualityTest instance = new WaterQualityTest();
            instance.setId(this.id);
            instance.setFarmer(this.farmer);
            instance.setTank(this.tank);
            instance.setTestDate(this.testDate);
            instance.setPh(this.ph);
            instance.setTdsOrConductivity(this.tdsOrConductivity);
            instance.setTurbidity(this.turbidity);
            instance.setNitrate(this.nitrate);
            instance.setColiformPresent(this.coliformPresent);
            instance.setVerdictDrinking(this.verdictDrinking);
            instance.setVerdictIrrigation(this.verdictIrrigation);
            instance.setAdviceJson(this.adviceJson);
            return instance;
        }
    }

}
