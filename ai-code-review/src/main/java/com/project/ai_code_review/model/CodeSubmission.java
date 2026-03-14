package com.project.ai_code_review.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name="code_submissions")
public class CodeSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

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
