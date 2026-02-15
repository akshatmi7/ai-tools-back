package com.aiintelli.aitoolsummarygenerator.config;

import com.aiintelli.aitoolsummarygenerator.AIToolService;
import com.aiintelli.aitoolsummarygenerator.ArticleService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Cache warmup service to preload frequently accessed data on application
 * startup.
 * This eliminates cold-start delays by ensuring cache is populated before first
 * user request.
 */
@Service
public class CacheWarmupService {

    private static final Logger logger = LoggerFactory.getLogger(CacheWarmupService.class);

    private final AIToolService aiToolService;
    private final ArticleService articleService;

    public CacheWarmupService(AIToolService aiToolService, ArticleService articleService) {
        this.aiToolService = aiToolService;
        this.articleService = articleService;
    }

    /**
     * Warmup cache on application startup.
     * Preloads all AI tools and latest articles into cache.
     * 
     * Expected warmup time: < 5 seconds
     */
    @PostConstruct
    public void warmupCache() {
        long startTime = System.currentTimeMillis();
        logger.info("🔥 Starting cache warmup...");

        try {
            // Preload AI tools
            int toolsCount = aiToolService.getAllTools().size();
            logger.info("✅ Preloaded {} AI tools into cache", toolsCount);

            // Preload articles
            int articlesCount = articleService.getLatestArticles().size();
            logger.info("✅ Preloaded {} articles into cache", articlesCount);

            long duration = System.currentTimeMillis() - startTime;
            logger.info("🎉 Cache warmup completed in {} ms", duration);

            if (duration > 5000) {
                logger.warn("⚠️ Cache warmup took longer than expected ({}ms). Consider optimizing queries.", duration);
            }

        } catch (Exception e) {
            logger.error("❌ Cache warmup failed: {}", e.getMessage(), e);
            // Don't fail application startup if warmup fails
        }
    }
}
