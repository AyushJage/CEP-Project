package com.dbit.rwh.controller;

import com.dbit.rwh.entity.PartnerOrganization;
import com.dbit.rwh.entity.ResearchEvidence;
import com.dbit.rwh.repository.PartnerOrganizationRepository;
import com.dbit.rwh.repository.ResearchEvidenceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContentController {

    private final PartnerOrganizationRepository partnerRepo;
    private final ResearchEvidenceRepository evidenceRepo;

    @GetMapping("/partners")
    public ResponseEntity<List<PartnerOrganization>> getPartners() {
        return ResponseEntity.ok(partnerRepo.findAll());
    }

    @GetMapping("/evidence")
    public ResponseEntity<List<ResearchEvidence>> getEvidence(@RequestParam(required = false) String category) {
        if (category != null && !category.isBlank()) {
            return ResponseEntity.ok(evidenceRepo.findAllByCategory(category.toUpperCase()));
        }
        return ResponseEntity.ok(evidenceRepo.findAll());
    }

    public ContentController(PartnerOrganizationRepository partnerRepo, ResearchEvidenceRepository evidenceRepo) {
        this.partnerRepo = partnerRepo;
        this.evidenceRepo = evidenceRepo;
    }

}
