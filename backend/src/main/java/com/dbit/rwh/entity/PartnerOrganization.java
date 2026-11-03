package com.dbit.rwh.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "partner_organization")
public class PartnerOrganization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name; // Paani Foundation, NAAM Foundation

    @Column(name = "focus_en", columnDefinition = "TEXT", nullable = false)
    private String focusEn;

    @Column(name = "focus_mr", columnDefinition = "TEXT", nullable = false)
    private String focusMr;

    @Column(name = "website_url", nullable = false)
    private String websiteUrl;

    @Column(name = "reported_note", columnDefinition = "TEXT")
    private String reportedNote;

    public PartnerOrganization() {}

    public PartnerOrganization(Long id, String name, String focusEn, String focusMr, String websiteUrl, String reportedNote) {
        this.id = id;
        this.name = name;
        this.focusEn = focusEn;
        this.focusMr = focusMr;
        this.websiteUrl = websiteUrl;
        this.reportedNote = reportedNote;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFocusEn() {
        return this.focusEn;
    }

    public void setFocusEn(String focusEn) {
        this.focusEn = focusEn;
    }

    public String getFocusMr() {
        return this.focusMr;
    }

    public void setFocusMr(String focusMr) {
        this.focusMr = focusMr;
    }

    public String getWebsiteUrl() {
        return this.websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getReportedNote() {
        return this.reportedNote;
    }

    public void setReportedNote(String reportedNote) {
        this.reportedNote = reportedNote;
    }

    public static PartnerOrganizationBuilder builder() {
        return new PartnerOrganizationBuilder();
    }

    public static class PartnerOrganizationBuilder {
        private Long id;
        private String name;
        private String focusEn;
        private String focusMr;
        private String websiteUrl;
        private String reportedNote;

        public PartnerOrganizationBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public PartnerOrganizationBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PartnerOrganizationBuilder focusEn(String focusEn) {
            this.focusEn = focusEn;
            return this;
        }

        public PartnerOrganizationBuilder focusMr(String focusMr) {
            this.focusMr = focusMr;
            return this;
        }

        public PartnerOrganizationBuilder websiteUrl(String websiteUrl) {
            this.websiteUrl = websiteUrl;
            return this;
        }

        public PartnerOrganizationBuilder reportedNote(String reportedNote) {
            this.reportedNote = reportedNote;
            return this;
        }

        public PartnerOrganization build() {
            PartnerOrganization instance = new PartnerOrganization();
            instance.setId(this.id);
            instance.setName(this.name);
            instance.setFocusEn(this.focusEn);
            instance.setFocusMr(this.focusMr);
            instance.setWebsiteUrl(this.websiteUrl);
            instance.setReportedNote(this.reportedNote);
            return instance;
        }
    }

}
