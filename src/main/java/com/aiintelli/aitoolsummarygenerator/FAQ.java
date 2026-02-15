package com.aiintelli.aitoolsummarygenerator;

import jakarta.persistence.*;

// COMMENTED OUT - Not needed for current frontend functionality
/*
 * @Entity
 * public class FAQ {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY)
 * private Long id;
 * 
 * @ManyToOne
 * 
 * @JoinColumn(name = "tool_id")
 * private AITool tool;
 * 
 * private String question;
 * 
 * @Column(columnDefinition = "TEXT")
 * private String answer;
 * 
 * public FAQ() {
 * }
 * 
 * public FAQ(Long id, AITool tool, String question, String answer) {
 * this.id = id;
 * this.tool = tool;
 * this.question = question;
 * this.answer = answer;
 * }
 * // Getters and Setters
 * 
 * public Long getId() {
 * return id;
 * }
 * 
 * public void setId(Long id) {
 * this.id = id;
 * }
 * 
 * public AITool getTool() {
 * return tool;
 * }
 * 
 * public void setTool(AITool tool) {
 * this.tool = tool;
 * }
 * 
 * public String getQuestion() {
 * return question;
 * }
 * 
 * public void setQuestion(String question) {
 * this.question = question;
 * }
 * 
 * public String getAnswer() {
 * return answer;
 * }
 * 
 * public void setAnswer(String answer) {
 * this.answer = answer;
 * }
 * }
 */
