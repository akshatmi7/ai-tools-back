package com.aiintelli.aitoolsummarygenerator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AIToolRepository extends JpaRepository<AITool, Long> {
    Optional<AITool> findByName(String name);
    Optional<AITool> findBySlug(String slug);
}

