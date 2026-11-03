package com.dbit.rwh.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "research_evidence")
public class ResearchEvidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_year", nullable = false, length = 100)
    private String authorYear; // e.g. "Pawar et al. (2014)"

    @Column(nullable = false, length = 100)
    private String region;     // e.g. "Sangli", "Akola (Vidarbha)", "Jalna"

    @Column(name = "finding_en", columnDefinition = "TEXT", nullable = false)
    private String findingEn;

    @Column(name = "finding_mr", columnDefinition = "TEXT", nullable = false)
    private String findingMr;

    @Column(length = 50)
    private String category;   // ROOFTOP, FARM_POND, CHECK_DAM, COMMUNITY, MACRO

    public ResearchEvidence() {}

    public ResearchEvidence(Long id, String authorYear, String region, String findingEn, String findingMr, String category) {
        this.id = id;
        this.authorYear = authorYear;
        this.region = region;
        this.findingEn = findingEn;
        this.findingMr = findingMr;
        this.category = category;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorYear() {
        return this.authorYear;
    }

    public void setAuthorYear(String authorYear) {
        this.authorYear = authorYear;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFindingEn() {
        return this.findingEn;
    }

    public void setFindingEn(String findingEn) {
        this.findingEn = findingEn;
    }

    public String getFindingMr() {
        return this.findingMr;
    }

    public void setFindingMr(String findingMr) {
        this.findingMr = findingMr;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static ResearchEvidenceBuilder builder() {
        return new ResearchEvidenceBuilder();
    }

    public static class ResearchEvidenceBuilder {
        private Long id;
        private String authorYear;
        private String region;
        private String findingEn;
        private String findingMr;
        private String category;

        public ResearchEvidenceBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ResearchEvidenceBuilder authorYear(String authorYear) {
            this.authorYear = authorYear;
            return this;
        }

        public ResearchEvidenceBuilder region(String region) {
            this.region = region;
            return this;
        }

        public ResearchEvidenceBuilder findingEn(String findingEn) {
            this.findingEn = findingEn;
            return this;
        }

        public ResearchEvidenceBuilder findingMr(String findingMr) {
            this.findingMr = findingMr;
            return this;
        }

        public ResearchEvidenceBuilder category(String category) {
            this.category = category;
            return this;
        }

        public ResearchEvidence build() {
            ResearchEvidence instance = new ResearchEvidence();
            instance.setId(this.id);
            instance.setAuthorYear(this.authorYear);
            instance.setRegion(this.region);
            instance.setFindingEn(this.findingEn);
            instance.setFindingMr(this.findingMr);
            instance.setCategory(this.category);
            return instance;
        }
    }

}
