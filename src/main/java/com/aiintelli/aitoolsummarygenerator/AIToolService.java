package com.aiintelli.aitoolsummarygenerator;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIToolService {

    private final AIToolRepository repository;

    public AIToolService(AIToolRepository repository) {
        this.repository = repository;
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
}