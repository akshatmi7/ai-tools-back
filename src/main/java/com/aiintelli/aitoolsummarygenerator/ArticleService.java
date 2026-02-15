package com.aiintelli.aitoolsummarygenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service layer for Article operations with enterprise-grade caching.
 */
@Service
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    /**
     * Add new article and invalidate cache.
     * Ensures cache consistency after data modification.
     */
    @CacheEvict(value = "articles", allEntries = true)
    public Article addArticle(Article article) {
        logger.info("Adding new article: {} (cache will be invalidated)", article.getTitle());
        return repository.save(article);
    }

    /**
     * Get latest articles with caching.
     * Cache is warmed up on application startup.
     * TTL: 1 hour
     */
    @Cacheable("articles")
    public List<Article> getLatestArticles() {
        long startTime = System.currentTimeMillis();
        logger.debug("Fetching articles from database");

        List<Article> articles = repository.findAllByOrderByCreatedAtDesc();

        long duration = System.currentTimeMillis() - startTime;
        logger.info("Fetched {} articles from database in {} ms", articles.size(), duration);

        return articles;
    }

    /**
     * Get article by ID (no caching for individual articles).
     */
    public Article getArticleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
    }
}
