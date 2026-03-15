package com.project.ai_code_review.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "code_submissions")
@Data
public class CodeSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;                    // ← primary key, UUID auto-generated

    @Column(name = "user_id")
    private String userId;              // ← just a regular column, NOT the PK

    @Column(columnDefinition = "TEXT")
    private String code;        // ← needed by CodeService.getCode()

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "language")
    private String language;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}