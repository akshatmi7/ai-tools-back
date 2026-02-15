package com.aiintelli.aitoolsummarygenerator.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Enterprise-grade Spring Cache configuration with Caffeine.
 * Service-layer caching (NOT Hibernate cache) - cloud-native pattern.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Caffeine cache configuration with production-optimized settings.
     * 
     * Settings:
     * - Initial capacity: 50 (prevents resizing overhead)
     * - Maximum size: 500 entries (memory-efficient)
     * - TTL: 10 minutes (balances freshness vs performance)
     * - Stats recording: enabled (for monitoring)
     */
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .initialCapacity(50)
                .maximumSize(500)
                .expireAfterWrite(Duration.ofMinutes(10))
                .recordStats(); // Enable for /actuator/caches metrics
    }

    /**
     * Spring Cache Manager using Caffeine.
     * Manages caches: aitools, articles, tool-by-slug
     */
    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager manager = new CaffeineCacheManager("aitools", "articles", "tool-by-slug");
        manager.setCaffeine(caffeine);
        return manager;
    }
}
