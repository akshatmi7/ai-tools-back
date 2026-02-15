package com.aiintelli.aitoolsummarygenerator;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class AITool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String slug;
    private String category;
    private String description;

    @Column(columnDefinition = "TEXT")
    private String fullDescription;

    @Column(columnDefinition = "LONGTEXT")
    private String researchText;

    private String iconUrl;
    private String website;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    // COMMENTED OUT - Not needed for current frontend functionality
    /*
     * @OneToMany(mappedBy = "tool", cascade = CascadeType.ALL)
     * private List<HowToUseStep> howToUseSteps;
     * 
     * @OneToMany(mappedBy = "tool", cascade = CascadeType.ALL)
     * private List<Pros> pros;
     * 
     * @OneToMany(mappedBy = "tool", cascade = CascadeType.ALL)
     * private List<Cons> cons;
     * 
     * @OneToMany(mappedBy = "tool", cascade = CascadeType.ALL)
     * private List<FAQ> faqs;
     */

    // Getters and Setters

    public AITool() {
    }

    // COMMENTED OUT - Constructor with related entities not needed
    /*
     * public AITool(Long id, String name, String slug, String category, String
     * description, String fullDescription, String researchText, String iconUrl,
     * String website, LocalDateTime createdAt, LocalDateTime updatedAt,
     * List<HowToUseStep> howToUseSteps, List<Pros> pros, List<Cons> cons, List<FAQ>
     * faqs) {
     * this.id = id;
     * this.name = name;
     * this.slug = slug;
     * this.category = category;
     * this.description = description;
     * this.fullDescription = fullDescription;
     * this.researchText = researchText;
     * this.iconUrl = iconUrl;
     * this.website = website;
     * this.createdAt = createdAt;
     * this.updatedAt = updatedAt;
     * this.howToUseSteps = howToUseSteps;
     * this.pros = pros;
     * this.cons = cons;
     * this.faqs = faqs;
     * }
     */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getResearchText() {
        return researchText;
    }

    public void setResearchText(String researchText) {
        this.researchText = researchText;
    }

    public String getIconUrl() {
        if (this.iconUrl != null && !this.iconUrl.isEmpty()) {
            return this.iconUrl;
        }
        if (this.website != null && !this.website.isEmpty()) {
            try {
                String domain = this.website;
                if (domain.startsWith("http://") || domain.startsWith("https://")) {
                    java.net.URI uri = new java.net.URI(domain);
                    domain = uri.getHost();
                }
                return "https://www.google.com/s2/favicons?domain=" + domain + "&sz=128";
            } catch (Exception e) {
                // Fallback will be handled by frontend
                return null;
            }
        }
        return null;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // COMMENTED OUT - Getters/Setters for related entities not needed
    /*
     * public List<HowToUseStep> getHowToUseSteps() {
     * return howToUseSteps;
     * }
     * 
     * public void setHowToUseSteps(List<HowToUseStep> howToUseSteps) {
     * this.howToUseSteps = howToUseSteps;
     * }
     * 
     * public List<Pros> getPros() {
     * return pros;
     * }
     * 
     * public void setPros(List<Pros> pros) {
     * this.pros = pros;
     * }
     * 
     * public List<Cons> getCons() {
     * return cons;
     * }
     * 
     * public void setCons(List<Cons> cons) {
     * this.cons = cons;
     * }
     * 
     * public List<FAQ> getFaqs() {
     * return faqs;
     * }
     * 
     * public void setFaqs(List<FAQ> faqs) {
     * this.faqs = faqs;
     * }
     */
}
