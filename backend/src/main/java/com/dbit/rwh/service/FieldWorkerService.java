package com.dbit.rwh.service;

import com.dbit.rwh.dto.RegisterRequest;
import com.dbit.rwh.dto.UserDto;
import com.dbit.rwh.entity.FarmerProfile;
import com.dbit.rwh.entity.Role;
import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.FarmerProfileRepository;
import com.dbit.rwh.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FieldWorkerService {

    private final UserRepository userRepo;
    private final FarmerProfileRepository profileRepo;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto registerFarmerOnBehalf(User fieldWorker, RegisterRequest req) {
        if (userRepo.existsByPhone(req.getPhone())) {
            throw new IllegalArgumentException("Farmer with phone number " + req.getPhone() + " is already registered.");
        }

        User farmer = User.builder()
                .name(req.getName())
                .phone(req.getPhone())
                .passwordHash(passwordEncoder.encode(req.getPassword()))
                .role(Role.FARMER)
                .village(req.getVillage())
                .taluka(req.getTaluka())
                .district(req.getDistrict())
                .language(req.getLanguage() != null ? req.getLanguage() : "mr")
                .build();
        userRepo.save(farmer);

        FarmerProfile profile = FarmerProfile.builder()
                .user(farmer)
                .assignedFieldWorker(fieldWorker)
                .build();
        profileRepo.save(profile);

        return UserDto.builder()
                .id(farmer.getId())
                .name(farmer.getName())
                .phone(farmer.getPhone())
                .role(farmer.getRole())
                .village(farmer.getVillage())
                .taluka(farmer.getTaluka())
                .district(farmer.getDistrict())
                .language(farmer.getLanguage())
                .build();
    }

    public List<UserDto> getAssignedFarmers(User fieldWorker) {
        List<FarmerProfile> profiles = profileRepo.findAllByAssignedFieldWorker(fieldWorker);
        return profiles.stream()
                .map(p -> {
                    User u = p.getUser();
                    return UserDto.builder()
                            .id(u.getId())
                            .name(u.getName())
                            .phone(u.getPhone())
                            .role(u.getRole())
                            .village(u.getVillage())
                            .taluka(u.getTaluka())
                            .district(u.getDistrict())
                            .language(u.getLanguage())
                            .build();
                })
                .toList();
    }

    public UserDto findFarmerByPhone(String phone) {
        User u = userRepo.findByPhone(phone)
                .orElseThrow(() -> new IllegalArgumentException("No farmer found with phone: " + phone));
        return UserDto.builder()
                .id(u.getId())
                .name(u.getName())
                .phone(u.getPhone())
                .role(u.getRole())
                .village(u.getVillage())
                .taluka(u.getTaluka())
                .district(u.getDistrict())
                .language(u.getLanguage())
                .build();
    }

    public FieldWorkerService(UserRepository userRepo, FarmerProfileRepository profileRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.profileRepo = profileRepo;
        this.passwordEncoder = passwordEncoder;
    }

}
