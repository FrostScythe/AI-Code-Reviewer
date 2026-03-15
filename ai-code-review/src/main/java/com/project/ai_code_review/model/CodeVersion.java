package com.project.ai_code_review.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "code_versions")
@Data
public class CodeVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submission_id", referencedColumnName = "id")  // ← explicitly points to 'id'
    private CodeSubmission submission;

    @Column(name = "version_number")
    private Integer versionNumber;

    @Column(columnDefinition = "TEXT")
    private String code;        // ← needed by CodeService

    @Column(columnDefinition = "TEXT")
    private String analysis;    // ← renamed from 'review' to match service calls

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}