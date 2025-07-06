package com.aiintelli.aitoolsummarygenerator;

import jakarta.persistence.*;

@Entity
public class HowToUseStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tool_id")
    private AITool tool;

    @Column(columnDefinition = "TEXT")
    private String stepText;

    // Getters and Setters
}
