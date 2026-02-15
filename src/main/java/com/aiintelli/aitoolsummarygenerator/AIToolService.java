package com.aiintelli.aitoolsummarygenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for AI Tool operations with enterprise-grade caching.
 */
@Service
public class AIToolService {

    private static final Logger logger = LoggerFactory.getLogger(AIToolService.class);

    private final AIToolRepository repository;

    public AIToolService(AIToolRepository repository) {
        this.repository = repository;
    }

    /**
     * Get tool by slug with caching.
     * Cache key: slug value
     */
    @Cacheable(value = "tool-by-slug", key = "#toolSlug")
    public AITool getTool(String toolSlug) {
        logger.debug("Fetching tool from database: {}", toolSlug);
        return repository.findBySlug(toolSlug)
                .orElseThrow(() -> new RuntimeException("Tool not found: " + toolSlug));
    }

    /**
     * Get all tools with caching.
     * Cache is warmed up on application startup.
     * TTL: 1 hour
     */
    @Cacheable("aitools")
    public List<AITool> getAllTools() {
        long startTime = System.currentTimeMillis();
        logger.debug("Fetching all tools from database");

        List<AITool> tools = repository.findAll();

        long duration = System.currentTimeMillis() - startTime;
        logger.info("Fetched {} tools from database in {} ms", tools.size(), duration);

        return tools;
    }

    /**
     * Add new tool and invalidate cache.
     * Ensures cache consistency after data modification.
     */
    @CacheEvict(value = { "aitools", "tool-by-slug" }, allEntries = true)
    public AITool addTool(AITool tool) {
        logger.info("Adding new tool: {} (cache will be invalidated)", tool.getName());
        return repository.save(tool);
    }
}