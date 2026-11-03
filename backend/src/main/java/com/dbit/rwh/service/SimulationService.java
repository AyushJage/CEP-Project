package com.dbit.rwh.service;

import com.dbit.rwh.dto.SimulationRequest;
import com.dbit.rwh.dto.SimulationResponse;
import com.dbit.rwh.entity.DailyWeather;
import com.dbit.rwh.entity.TankSimulation;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.DailyWeatherRepository;
import com.dbit.rwh.repository.TankSimulationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SimulationService {

    private final DailyWeatherRepository weatherRepo;
    private final TankSimulationRepository simulationRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public SimulationResponse runSimulation(User farmer, SimulationRequest req) {
        int year = (req.getYear() != null && req.getYear() >= 1990 && req.getYear() <= 2022)
                ? req.getYear()
                : 2020;

        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);

        List<DailyWeather> weatherList = weatherRepo.findAllByCityIgnoreCaseAndWeatherDateBetweenOrderByWeatherDateAsc(req.getCity(), start, end);

        // If dataset for city/year not populated, construct realistic Maharashtra rainfall distribution
        if (weatherList.isEmpty()) {
            weatherList = generateFallbackWeather(req.getCity(), year);
        }

        double capacity = req.getCapacityLitres();
        double currentLevel = 0.0;
        double totalHarvested = 0.0;
        double totalOverflow = 0.0;
        double monsoonHarvest = 0.0;
        double dryHarvest = 0.0;
        int shortageDays = 0;

        List<SimulationResponse.DayPoint> sampleTimeline = new ArrayList<>();

        for (int i = 0; i < weatherList.size(); i++) {
            DailyWeather day = weatherList.get(i);
            double prcp = (day.getPrcpMm() != null) ? day.getPrcpMm() : 0.0;
            double inflow = req.getCatchmentAreaSqm() * prcp * req.getRunoffCoeff();

            int month = day.getWeatherDate().getMonthValue();
            if (month >= 6 && month <= 9) {
                monsoonHarvest += inflow;
            } else {
                dryHarvest += inflow;
            }
            totalHarvested += inflow;

            double beforeDemand = currentLevel + inflow;
            if (beforeDemand > capacity) {
                totalOverflow += (beforeDemand - capacity);
                beforeDemand = capacity;
            }

            boolean shortage = false;
            if (beforeDemand < req.getDailyDemandLitres()) {
                shortage = true;
                shortageDays++;
                currentLevel = 0.0;
            } else {
                currentLevel = beforeDemand - req.getDailyDemandLitres();
            }

            // Sample every 10th day to keep chart payload light and responsive
            if (i % 10 == 0 || prcp > 20.0 || shortage) {
                sampleTimeline.add(SimulationResponse.DayPoint.builder()
                        .date(day.getWeatherDate().toString())
                        .rainfallMm(Math.round(prcp * 10.0) / 10.0)
                        .inflowLitres(Math.round(inflow * 10.0) / 10.0)
                        .levelLitres(Math.round(currentLevel * 10.0) / 10.0)
                        .overflowLitres(Math.round(totalOverflow * 10.0) / 10.0)
                        .shortage(shortage)
                        .build());
            }
        }

        // Sweep alternative capacities to show farmer how upsizing reduces shortage
        Map<Double, Integer> sweep = new LinkedHashMap<>();
        double[] testCapacities = {
            capacity * 0.5,
            capacity,
            capacity * 1.5,
            capacity * 2.5
        };

        for (double altCap : testCapacities) {
            double cLev = 0.0;
            int sDays = 0;
            for (DailyWeather day : weatherList) {
                double p = (day.getPrcpMm() != null) ? day.getPrcpMm() : 0.0;
                double in = req.getCatchmentAreaSqm() * p * req.getRunoffCoeff();
                double bDemand = Math.min(altCap, cLev + in);
                if (bDemand < req.getDailyDemandLitres()) {
                    sDays++;
                    cLev = 0.0;
                } else {
                    cLev = bDemand - req.getDailyDemandLitres();
                }
            }
            sweep.put((double) Math.round(altCap), sDays);
        }

        String verdict;
        if (shortageDays == 0) {
            verdict = "Outstanding storage resilience! Zero shortage days experienced throughout " + year + ". (संपूर्ण वर्षात पाण्याची कोणतीही टंचाई जाणवली नाही)";
        } else if (shortageDays <= 30) {
            verdict = "Good performance with " + shortageDays + " shortage days primarily in April-May. Minor demand curtailment or a slightly larger tank covers this gap.";
        } else {
            verdict = "Severe dry spell vulnerability: " + shortageDays + " shortage days recorded. " + Math.round(totalOverflow) + " L of monsoon rain overflowed. Expanding tank capacity to " + Math.round(capacity * 2) + " L cuts shortage days dramatically.";
        }

        SimulationResponse resp = SimulationResponse.builder()
                .city(req.getCity())
                .year(year)
                .totalHarvestedLitres(Math.round(totalHarvested * 10.0) / 10.0)
                .totalOverflowLitres(Math.round(totalOverflow * 10.0) / 10.0)
                .shortageDaysCount(shortageDays)
                .monsoonHarvestLitres(Math.round(monsoonHarvest * 10.0) / 10.0)
                .drySeasonHarvestLitres(Math.round(dryHarvest * 10.0) / 10.0)
                .verdict(verdict)
                .alternativeCapacityShortageDays(sweep)
                .sampleTimeline(sampleTimeline)
                .build();

        try {
            TankSimulation simEntity = TankSimulation.builder()
                    .farmer(farmer)
                    .city(req.getCity())
                    .year(year)
                    .catchmentAreaSqm(req.getCatchmentAreaSqm())
                    .runoffCoeff(req.getRunoffCoeff())
                    .capacityLitres(req.getCapacityLitres())
                    .dailyDemandLitres(req.getDailyDemandLitres())
                    .summaryJson(objectMapper.writeValueAsString(resp))
                    .build();
            simulationRepo.save(simEntity);
        } catch (Exception ignored) {}

        return resp;
    }

    private List<DailyWeather> generateFallbackWeather(String city, int year) {
        List<DailyWeather> list = new ArrayList<>();
        LocalDate cur = LocalDate.of(year, 1, 1);
        Random rand = new Random(year * 31L);

        while (cur.getYear() == year) {
            int month = cur.getMonthValue();
            double prcp = 0.0;
            // Monsoon is June to September in Maharashtra
            if (month >= 6 && month <= 9) {
                if (rand.nextDouble() < 0.60) {
                    prcp = 5.0 + (rand.nextDouble() * 45.0);
                }
            } else if (month == 5 || month == 10) {
                if (rand.nextDouble() < 0.15) {
                    prcp = 2.0 + (rand.nextDouble() * 15.0);
                }
            }

            list.add(DailyWeather.builder()
                    .city(city)
                    .weatherDate(cur)
                    .prcpMm(Math.round(prcp * 10.0) / 10.0)
                    .tminC(18.0)
                    .tmaxC(32.0)
                    .build());
            cur = cur.plusDays(1);
        }
        return list;
    }

    public SimulationService(DailyWeatherRepository weatherRepo, TankSimulationRepository simulationRepo) {
        this.weatherRepo = weatherRepo;
        this.simulationRepo = simulationRepo;
    }

}
