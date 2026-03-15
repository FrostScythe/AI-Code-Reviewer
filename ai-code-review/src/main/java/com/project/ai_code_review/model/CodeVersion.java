package com.project.ai_code_review.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    // LAZY causes LazyInitializationException during JSON serialization.
    //  @JsonIgnore prevents infinite loop in serialization.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "submission_id", referencedColumnName = "id")
    @JsonIgnore
    private CodeSubmission submission;

    @Column(name = "version_number")
    private Integer versionNumber;

    @Column(columnDefinition = "TEXT")
    private String code;

    @Column(columnDefinition = "TEXT")
    private String analysis;

    @Column(name = "status")
    private String status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}