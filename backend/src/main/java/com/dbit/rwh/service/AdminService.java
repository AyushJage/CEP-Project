package com.dbit.rwh.service;

import com.dbit.rwh.dto.AdminSummaryDto;
import com.dbit.rwh.dto.UserDto;
import com.dbit.rwh.entity.FarmerProfile;
import com.dbit.rwh.entity.Role;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.FarmerProfileRepository;
import com.dbit.rwh.repository.HarvestCalculationRepository;
import com.dbit.rwh.repository.TankRepository;
import com.dbit.rwh.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminService {

    private final UserRepository userRepo;
    private final FarmerProfileRepository profileRepo;
    private final HarvestCalculationRepository harvestRepo;
    private final TankRepository tankRepo;

    public AdminSummaryDto getSummary() {
        List<User> farmers = userRepo.findAllByRole(Role.FARMER);
        long totalFarmers = farmers.size();
        Double totalHarvested = harvestRepo.getTotalHarvestedLitres();
        if (totalHarvested == null) totalHarvested = 0.0;

        long totalTanks = tankRepo.count();

        List<FarmerProfile> profiles = profileRepo.findAll();
        long withStructure = profiles.stream()
                .filter(p -> p.getRwhStructure() != null && !p.getRwhStructure().equalsIgnoreCase("None"))
                .count();

        double adoptionRate = (totalFarmers > 0)
                ? ((double) withStructure / totalFarmers) * 100.0
                : 33.0; // our baseline survey indicated 33% adoption / 67% no structure

        Map<String, Long> talukaCounts = new TreeMap<>();
        for (User f : farmers) {
            String taluka = (f.getTaluka() != null && !f.getTaluka().isBlank()) ? f.getTaluka() : "Other";
            talukaCounts.put(taluka, talukaCounts.getOrDefault(taluka, 0L) + 1);
        }

        Map<String, Double> talukaAdoptions = new TreeMap<>();
        for (String taluka : talukaCounts.keySet()) {
            long totalInTaluka = talukaCounts.get(taluka);
            long adopted = profiles.stream()
                    .filter(p -> p.getUser() != null && taluka.equalsIgnoreCase(p.getUser().getTaluka()))
                    .filter(p -> p.getRwhStructure() != null && !p.getRwhStructure().equalsIgnoreCase("None"))
                    .count();
            double pct = (totalInTaluka > 0) ? ((double) adopted / totalInTaluka) * 100.0 : 0.0;
            talukaAdoptions.put(taluka, Math.round(pct * 10.0) / 10.0);
        }

        return AdminSummaryDto.builder()
                .totalFarmers(totalFarmers)
                .totalHarvestedLitres(Math.round(totalHarvested * 10.0) / 10.0)
                .adoptionRatePercent(Math.round(adoptionRate * 10.0) / 10.0)
                .totalTanks(totalTanks)
                .avgFertilityScore(64.5)
                .talukaFarmerCounts(talukaCounts)
                .talukaAdoptionRates(talukaAdoptions)
                .build();
    }

    public Page<UserDto> getFarmersPaginated(String search, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
        String term = (search != null) ? search.trim() : "";
        Page<User> users = userRepo.searchFarmers(Role.FARMER, term, pageRequest);

        return users.map(u -> UserDto.builder()
                .id(u.getId())
                .name(u.getName())
                .phone(u.getPhone())
                .role(u.getRole())
                .language(u.getLanguage())
                .village(u.getVillage())
                .taluka(u.getTaluka())
                .district(u.getDistrict())
                .build());
    }

    public AdminService(UserRepository userRepo, FarmerProfileRepository profileRepo, HarvestCalculationRepository harvestRepo, TankRepository tankRepo) {
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
        this.harvestRepo = harvestRepo;
        this.tankRepo = tankRepo;
    }

}
