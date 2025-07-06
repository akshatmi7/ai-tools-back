package com.aiintelli.aitoolsummarygenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai-tools")
public class AIToolController {

    private final AIToolService service;

    @Autowired
    public AIToolController(AIToolService service) {
        this.service = service;
    }

    @GetMapping("/{slug}")
    public ResponseEntity<AITool> getTool(@PathVariable String slug) {
        return ResponseEntity.ok(service.getTool(slug));
    }

    @GetMapping
    public ResponseEntity<List<AITool>> getAll() {
        return ResponseEntity.ok(service.getAllTools());
    }

    @PostMapping
    public ResponseEntity<AITool> addTool(@RequestBody AITool tool) {
        return ResponseEntity.ok(service.addTool(tool));
    }

    @PostMapping("/{slug}/regenerate")
    public ResponseEntity<AITool> regenerateResearch(@PathVariable String slug) {
        return ResponseEntity.ok(service.regenerateResearch(slug));
    }


}
