package com.project.ai_code_review.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "code_submissions")
public class CodeSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    // Separate field to track which user owns this submission
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    private String fileName;
    private String language;

    @Column(columnDefinition = "TEXT")
    private String code;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreated() {
        createdAt = LocalDateTime.now();
    }
}