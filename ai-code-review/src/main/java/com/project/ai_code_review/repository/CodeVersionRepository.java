package com.project.ai_code_review.repository;

import com.project.ai_code_review.model.CodeVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CodeVersionRepository extends JpaRepository<CodeVersion, UUID> {

    // Used by VersionService.getVersions() — returns all versions newest first
    List<CodeVersion> findBySubmissionIdOrderByVersionNumberDesc(UUID submissionId);

    // Used by VersionService.getLatestVersion() and createNewVersion()
    Optional<CodeVersion> findTopBySubmissionIdOrderByVersionNumberDesc(UUID submissionId);
}