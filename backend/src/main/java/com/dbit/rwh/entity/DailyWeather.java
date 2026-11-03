package com.dbit.rwh.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "daily_weather", indexes = {
    @Index(name = "idx_weather_city_date", columnList = "city, weather_date")
})
public class DailyWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String city; // Mumbai, Pune, Nagpur, etc.

    @Column(name = "weather_date", nullable = false)
    private LocalDate weatherDate;

    @Column(name = "prcp_mm")
    private Double prcpMm;

    @Column(name = "tmin_c")
    private Double tminC;

    @Column(name = "tmax_c")
    private Double tmaxC;

    public DailyWeather() {}

    public DailyWeather(Long id, String city, LocalDate weatherDate, Double prcpMm, Double tminC, Double tmaxC) {
        this.id = id;
        this.city = city;
        this.weatherDate = weatherDate;
        this.prcpMm = prcpMm;
        this.tminC = tminC;
        this.tmaxC = tmaxC;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getWeatherDate() {
        return this.weatherDate;
    }

    public void setWeatherDate(LocalDate weatherDate) {
        this.weatherDate = weatherDate;
    }

    public Double getPrcpMm() {
        return this.prcpMm;
    }

    public void setPrcpMm(Double prcpMm) {
        this.prcpMm = prcpMm;
    }

    public Double getTminC() {
        return this.tminC;
    }

    public void setTminC(Double tminC) {
        this.tminC = tminC;
    }

    public Double getTmaxC() {
        return this.tmaxC;
    }

    public void setTmaxC(Double tmaxC) {
        this.tmaxC = tmaxC;
    }

    public static DailyWeatherBuilder builder() {
        return new DailyWeatherBuilder();
    }

    public static class DailyWeatherBuilder {
        private Long id;
        private String city;
        private LocalDate weatherDate;
        private Double prcpMm;
        private Double tminC;
        private Double tmaxC;

        public DailyWeatherBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DailyWeatherBuilder city(String city) {
            this.city = city;
            return this;
        }

        public DailyWeatherBuilder weatherDate(LocalDate weatherDate) {
            this.weatherDate = weatherDate;
            return this;
        }

        public DailyWeatherBuilder prcpMm(Double prcpMm) {
            this.prcpMm = prcpMm;
            return this;
        }

        public DailyWeatherBuilder tminC(Double tminC) {
            this.tminC = tminC;
            return this;
        }

        public DailyWeatherBuilder tmaxC(Double tmaxC) {
            this.tmaxC = tmaxC;
            return this;
        }

        public DailyWeather build() {
            DailyWeather instance = new DailyWeather();
            instance.setId(this.id);
            instance.setCity(this.city);
            instance.setWeatherDate(this.weatherDate);
            instance.setPrcpMm(this.prcpMm);
            instance.setTminC(this.tminC);
            instance.setTmaxC(this.tmaxC);
            return instance;
        }
    }

}
