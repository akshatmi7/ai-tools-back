package com.aiintelli.aitoolsummarygenerator;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public Article addArticle(Article article) {
        return repository.save(article);
    }

    public List<Article> getLatestArticles() {
        return repository.findAllByOrderByCreatedAtDesc();
    }

    public Article getArticleById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
    }
}
