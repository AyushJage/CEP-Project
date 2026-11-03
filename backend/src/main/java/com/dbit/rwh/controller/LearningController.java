package com.dbit.rwh.controller;

import com.dbit.rwh.dto.LearningGuideDto;
import com.dbit.rwh.entity.LearningGuide;
import com.dbit.rwh.repository.LearningGuideRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/learning")
public class LearningController {

    private final LearningGuideRepository learningRepo;

    @GetMapping
    public ResponseEntity<List<LearningGuideDto>> getGuides(@RequestParam(required = false) String category,
                                                            @RequestParam(required = false) String situation) {
        List<LearningGuide> list;
        if (category != null && !category.isBlank()) {
            list = learningRepo.findAllByCategory(category.toUpperCase());
        } else if (situation != null && !situation.isBlank()) {
            list = learningRepo.findByTargetSituationContaining(situation);
        } else {
            list = learningRepo.findAll();
        }

        List<LearningGuideDto> dtoList = list.stream().map(this::toDto).toList();
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LearningGuideDto> getGuide(@PathVariable Long id) {
        LearningGuide g = learningRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Guide not found with ID: " + id));
        return ResponseEntity.ok(toDto(g));
    }

    private LearningGuideDto toDto(LearningGuide g) {
        return LearningGuideDto.builder()
                .id(g.getId())
                .titleEn(g.getTitleEn())
                .titleMr(g.getTitleMr())
                .bodyEn(g.getBodyEn())
                .bodyMr(g.getBodyMr())
                .category(g.getCategory())
                .targetSituation(g.getTargetSituation())
                .imageUrl(g.getImageUrl())
                .build();
    }

    public LearningController(LearningGuideRepository learningRepo) {
        this.learningRepo = learningRepo;
    }

}
