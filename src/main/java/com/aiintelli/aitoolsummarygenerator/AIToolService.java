package com.aiintelli.aitoolsummarygenerator;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class AIToolService {

    private final AIToolRepository repository;
    private final GeminiService geminiService;

    public AIToolService(AIToolRepository repository, GeminiService geminiService) {
        this.repository = repository;
        this.geminiService = geminiService;
    }

    public AITool getTool(String toolSlug) {
        return repository.findBySlug(toolSlug)
                .orElseThrow(() -> new RuntimeException("Tool not found: " + toolSlug));
    }

    public List<AITool> getAllTools() {
        return repository.findAll();
    }

    public AITool addTool(AITool tool) {
        return repository.save(tool);
    }

    public AITool regenerateResearch(String toolSlug) {
        AITool tool = repository.findBySlug(toolSlug)
                .orElseThrow(() -> new RuntimeException("Tool not found for regenerate: " + toolSlug));

        String prompt = "Give a detailed research summary of the AI tool: " + tool.getName();
        String researchSummary = geminiService.generateResearch(prompt);

        tool.setResearchText(researchSummary);
        tool.setUpdatedAt(LocalDateTime.now());
        return repository.save(tool);
    }
}