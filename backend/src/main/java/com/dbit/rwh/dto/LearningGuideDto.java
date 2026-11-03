package com.dbit.rwh.dto;


public class LearningGuideDto {
    private Long id;
    private String titleEn;
    private String titleMr;
    private String bodyEn;
    private String bodyMr;
    private String category;
    private String targetSituation;
    private String imageUrl;

    public LearningGuideDto() {}

    public LearningGuideDto(Long id, String titleEn, String titleMr, String bodyEn, String bodyMr, String category, String targetSituation, String imageUrl) {
        this.id = id;
        this.titleEn = titleEn;
        this.titleMr = titleMr;
        this.bodyEn = bodyEn;
        this.bodyMr = bodyMr;
        this.category = category;
        this.targetSituation = targetSituation;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleEn() {
        return this.titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getTitleMr() {
        return this.titleMr;
    }

    public void setTitleMr(String titleMr) {
        this.titleMr = titleMr;
    }

    public String getBodyEn() {
        return this.bodyEn;
    }

    public void setBodyEn(String bodyEn) {
        this.bodyEn = bodyEn;
    }

    public String getBodyMr() {
        return this.bodyMr;
    }

    public void setBodyMr(String bodyMr) {
        this.bodyMr = bodyMr;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTargetSituation() {
        return this.targetSituation;
    }

    public void setTargetSituation(String targetSituation) {
        this.targetSituation = targetSituation;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static LearningGuideDtoBuilder builder() {
        return new LearningGuideDtoBuilder();
    }

    public static class LearningGuideDtoBuilder {
        private Long id;
        private String titleEn;
        private String titleMr;
        private String bodyEn;
        private String bodyMr;
        private String category;
        private String targetSituation;
        private String imageUrl;

        public LearningGuideDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LearningGuideDtoBuilder titleEn(String titleEn) {
            this.titleEn = titleEn;
            return this;
        }

        public LearningGuideDtoBuilder titleMr(String titleMr) {
            this.titleMr = titleMr;
            return this;
        }

        public LearningGuideDtoBuilder bodyEn(String bodyEn) {
            this.bodyEn = bodyEn;
            return this;
        }

        public LearningGuideDtoBuilder bodyMr(String bodyMr) {
            this.bodyMr = bodyMr;
            return this;
        }

        public LearningGuideDtoBuilder category(String category) {
            this.category = category;
            return this;
        }

        public LearningGuideDtoBuilder targetSituation(String targetSituation) {
            this.targetSituation = targetSituation;
            return this;
        }

        public LearningGuideDtoBuilder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public LearningGuideDto build() {
            LearningGuideDto instance = new LearningGuideDto();
            instance.setId(this.id);
            instance.setTitleEn(this.titleEn);
            instance.setTitleMr(this.titleMr);
            instance.setBodyEn(this.bodyEn);
            instance.setBodyMr(this.bodyMr);
            instance.setCategory(this.category);
            instance.setTargetSituation(this.targetSituation);
            instance.setImageUrl(this.imageUrl);
            return instance;
        }
    }

}
