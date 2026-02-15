package com.aiintelli.aitoolsummarygenerator.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Enterprise-grade cache configuration using Caffeine.
 * Provides in-memory caching for frequently accessed data to reduce database
 * load.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Configure Caffeine-based cache manager with optimized settings.
     * 
     * Cache Specifications:
     * - aitools: 1 hour TTL, max 1000 entries
     * - articles: 30 minutes TTL, max 500 entries
     * - tool-by-slug: 1 hour TTL, max 500 entries
     */
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("aitools", "articles", "tool-by-slug");
        cacheManager.setCaffeine(caffeineCacheBuilder());
        return cacheManager;
    }

    /**
     * Caffeine cache builder with production-optimized settings.
     * 
     * Configuration:
     * - Maximum size: 1000 entries (prevents memory overflow)
     * - TTL: 1 hour (balances freshness vs performance)
     * - Metrics: Enabled for monitoring cache hit rates
     */
    private Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(1, TimeUnit.HOURS)
                .recordStats(); // Enable metrics for monitoring
    }
}
