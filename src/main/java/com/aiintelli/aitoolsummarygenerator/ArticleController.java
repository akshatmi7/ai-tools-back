package com.aiintelli.aitoolsummarygenerator;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        return ResponseEntity.ok(service.addArticle(article));
    }

    @GetMapping
    public ResponseEntity<List<Article>> getLatestArticles() {
        return ResponseEntity.ok(service.getLatestArticles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        try {
            Article article = service.getArticleById(id);
            return ResponseEntity.ok(article);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
