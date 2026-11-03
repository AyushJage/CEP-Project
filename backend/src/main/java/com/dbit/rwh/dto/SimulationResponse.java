package com.dbit.rwh.dto;

import java.util.List;
import java.util.Map;

public class SimulationResponse {

    public static class DayPoint {
        private String date;
        private Double rainfallMm;
        private Double inflowLitres;
        private Double levelLitres;
        private Double overflowLitres;
        private Boolean shortage;

        public DayPoint() {}

        public DayPoint(String date, Double rainfallMm, Double inflowLitres, Double levelLitres, Double overflowLitres, Boolean shortage) {
            this.date = date;
            this.rainfallMm = rainfallMm;
            this.inflowLitres = inflowLitres;
            this.levelLitres = levelLitres;
            this.overflowLitres = overflowLitres;
            this.shortage = shortage;
        }

        public String getDate() { return date; }
        public void setDate(String date) { this.date = date; }

        public Double getRainfallMm() { return rainfallMm; }
        public void setRainfallMm(Double rainfallMm) { this.rainfallMm = rainfallMm; }

        public Double getInflowLitres() { return inflowLitres; }
        public void setInflowLitres(Double inflowLitres) { this.inflowLitres = inflowLitres; }

        public Double getLevelLitres() { return levelLitres; }
        public void setLevelLitres(Double levelLitres) { this.levelLitres = levelLitres; }

        public Double getOverflowLitres() { return overflowLitres; }
        public void setOverflowLitres(Double overflowLitres) { this.overflowLitres = overflowLitres; }

        public Boolean getShortage() { return shortage; }
        public void setShortage(Boolean shortage) { this.shortage = shortage; }

        public static DayPointBuilder builder() { return new DayPointBuilder(); }

        public static class DayPointBuilder {
            private String date;
            private Double rainfallMm;
            private Double inflowLitres;
            private Double levelLitres;
            private Double overflowLitres;
            private Boolean shortage;

            public DayPointBuilder date(String date) { this.date = date; return this; }
            public DayPointBuilder rainfallMm(Double rainfallMm) { this.rainfallMm = rainfallMm; return this; }
            public DayPointBuilder inflowLitres(Double inflowLitres) { this.inflowLitres = inflowLitres; return this; }
            public DayPointBuilder levelLitres(Double levelLitres) { this.levelLitres = levelLitres; return this; }
            public DayPointBuilder overflowLitres(Double overflowLitres) { this.overflowLitres = overflowLitres; return this; }
            public DayPointBuilder shortage(Boolean shortage) { this.shortage = shortage; return this; }

            public DayPoint build() {
                return new DayPoint(date, rainfallMm, inflowLitres, levelLitres, overflowLitres, shortage);
            }
        }
    }

    private String city;
    private Integer year;
    private Double totalHarvestedLitres;
    private Double totalOverflowLitres;
    private Integer shortageDaysCount;
    private Double monsoonHarvestLitres;
    private Double drySeasonHarvestLitres;
    private String verdict;
    private Map<Double, Integer> alternativeCapacityShortageDays;
    private List<DayPoint> sampleTimeline;

    public SimulationResponse() {}

    public SimulationResponse(String city, Integer year, Double totalHarvestedLitres, Double totalOverflowLitres,
                              Integer shortageDaysCount, Double monsoonHarvestLitres, Double drySeasonHarvestLitres,
                              String verdict, Map<Double, Integer> alternativeCapacityShortageDays, List<DayPoint> sampleTimeline) {
        this.city = city;
        this.year = year;
        this.totalHarvestedLitres = totalHarvestedLitres;
        this.totalOverflowLitres = totalOverflowLitres;
        this.shortageDaysCount = shortageDaysCount;
        this.monsoonHarvestLitres = monsoonHarvestLitres;
        this.drySeasonHarvestLitres = drySeasonHarvestLitres;
        this.verdict = verdict;
        this.alternativeCapacityShortageDays = alternativeCapacityShortageDays;
        this.sampleTimeline = sampleTimeline;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public Double getTotalHarvestedLitres() { return totalHarvestedLitres; }
    public void setTotalHarvestedLitres(Double totalHarvestedLitres) { this.totalHarvestedLitres = totalHarvestedLitres; }

    public Double getTotalOverflowLitres() { return totalOverflowLitres; }
    public void setTotalOverflowLitres(Double totalOverflowLitres) { this.totalOverflowLitres = totalOverflowLitres; }

    public Integer getShortageDaysCount() { return shortageDaysCount; }
    public void setShortageDaysCount(Integer shortageDaysCount) { this.shortageDaysCount = shortageDaysCount; }

    public Double getMonsoonHarvestLitres() { return monsoonHarvestLitres; }
    public void setMonsoonHarvestLitres(Double monsoonHarvestLitres) { this.monsoonHarvestLitres = monsoonHarvestLitres; }

    public Double getDrySeasonHarvestLitres() { return drySeasonHarvestLitres; }
    public void setDrySeasonHarvestLitres(Double drySeasonHarvestLitres) { this.drySeasonHarvestLitres = drySeasonHarvestLitres; }

    public String getVerdict() { return verdict; }
    public void setVerdict(String verdict) { this.verdict = verdict; }

    public Map<Double, Integer> getAlternativeCapacityShortageDays() { return alternativeCapacityShortageDays; }
    public void setAlternativeCapacityShortageDays(Map<Double, Integer> alternativeCapacityShortageDays) { this.alternativeCapacityShortageDays = alternativeCapacityShortageDays; }

    public List<DayPoint> getSampleTimeline() { return sampleTimeline; }
    public void setSampleTimeline(List<DayPoint> sampleTimeline) { this.sampleTimeline = sampleTimeline; }

    public static SimulationResponseBuilder builder() { return new SimulationResponseBuilder(); }

    public static class SimulationResponseBuilder {
        private String city;
        private Integer year;
        private Double totalHarvestedLitres;
        private Double totalOverflowLitres;
        private Integer shortageDaysCount;
        private Double monsoonHarvestLitres;
        private Double drySeasonHarvestLitres;
        private String verdict;
        private Map<Double, Integer> alternativeCapacityShortageDays;
        private List<DayPoint> sampleTimeline;

        public SimulationResponseBuilder city(String city) { this.city = city; return this; }
        public SimulationResponseBuilder year(Integer year) { this.year = year; return this; }
        public SimulationResponseBuilder totalHarvestedLitres(Double totalHarvestedLitres) { this.totalHarvestedLitres = totalHarvestedLitres; return this; }
        public SimulationResponseBuilder totalOverflowLitres(Double totalOverflowLitres) { this.totalOverflowLitres = totalOverflowLitres; return this; }
        public SimulationResponseBuilder shortageDaysCount(Integer shortageDaysCount) { this.shortageDaysCount = shortageDaysCount; return this; }
        public SimulationResponseBuilder monsoonHarvestLitres(Double monsoonHarvestLitres) { this.monsoonHarvestLitres = monsoonHarvestLitres; return this; }
        public SimulationResponseBuilder drySeasonHarvestLitres(Double drySeasonHarvestLitres) { this.drySeasonHarvestLitres = drySeasonHarvestLitres; return this; }
        public SimulationResponseBuilder verdict(String verdict) { this.verdict = verdict; return this; }
        public SimulationResponseBuilder alternativeCapacityShortageDays(Map<Double, Integer> alternativeCapacityShortageDays) { this.alternativeCapacityShortageDays = alternativeCapacityShortageDays; return this; }
        public SimulationResponseBuilder sampleTimeline(List<DayPoint> sampleTimeline) { this.sampleTimeline = sampleTimeline; return this; }

        public SimulationResponse build() {
            return new SimulationResponse(city, year, totalHarvestedLitres, totalOverflowLitres, shortageDaysCount,
                    monsoonHarvestLitres, drySeasonHarvestLitres, verdict, alternativeCapacityShortageDays, sampleTimeline);
        }
    }
}
