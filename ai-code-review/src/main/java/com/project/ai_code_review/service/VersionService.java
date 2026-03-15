package com.project.ai_code_review.service;

import com.project.ai_code_review.model.CodeSubmission;
import com.project.ai_code_review.model.CodeVersion;
import com.project.ai_code_review.repository.CodeSubmissionRepository;
import com.project.ai_code_review.repository.CodeVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VersionService {

    @Autowired
    private CodeVersionRepository versionRepository;

    @Autowired
    private CodeSubmissionRepository submissionRepository;

    @Autowired
    private AIService aiService;

    /**
     * Get all versions for a submission, newest first.
     */
    public List<CodeVersion> getVersions(UUID submissionId) {
        return versionRepository.findBySubmissionIdOrderByVersionNumberDesc(submissionId);
    }

    /**
     * Get the latest version for a submission.
     */
    public Optional<CodeVersion> getLatestVersion(UUID submissionId) {
        return versionRepository.findTopBySubmissionIdOrderByVersionNumberDesc(submissionId);
    }

    /**
     * Run AI analysis on a specific version by its ID.
     */
    public CodeVersion analyzeVersion(UUID versionId) {
        CodeVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new RuntimeException("Version not found: " + versionId));
        String analysis = aiService.analyzeCode(version.getCode());
        version.setAnalysis(analysis);
        return versionRepository.save(version);
    }

    /**
     * Create a new version for a submission with the provided code,
     * then auto-trigger AI analysis on it.
     */
    public CodeVersion createNewVersion(UUID submissionId, String code) {
        CodeSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found: " + submissionId));

        // Calculate next version number
        int nextVersion = versionRepository
                .findTopBySubmissionIdOrderByVersionNumberDesc(submissionId)
                .map(v -> v.getVersionNumber() + 1)
                .orElse(1);

        CodeVersion version = new CodeVersion();
        version.setSubmission(submission);
        version.setVersionNumber(nextVersion);
        version.setCode(code);
        version.setAnalysis("Pending analysis...");
        CodeVersion saved = versionRepository.save(version);

        // Immediately run AI analysis
        String analysis = aiService.analyzeCode(code);
        saved.setAnalysis(analysis);
        return versionRepository.save(saved);
    }
}