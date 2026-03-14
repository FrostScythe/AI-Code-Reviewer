package com.project.ai_code_review.service;

import com.project.ai_code_review.model.CodeVersion;
import com.project.ai_code_review.repository.CodeVersionRepository;
import com.project.ai_code_review.service.AIService;   // ← fixed import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service   // ← was missing entirely
public class VersionService {

    @Autowired
    private CodeVersionRepository versionRepository;

    @Autowired
    private AIService aiService;

    public List<CodeVersion> getVersions(UUID submissionId) {
        return versionRepository.findBySubmissionId(submissionId);
    }

    public CodeVersion analyzeVersion(UUID versionId) {   // ← renamed to match controller
        CodeVersion version = versionRepository.findById(versionId)
                .orElseThrow(() -> new RuntimeException("Version not found"));
        String analysis = aiService.analyzeCode(version.getCode());
        version.setAnalysis(analysis);
        return versionRepository.save(version);
    }

    public Optional<CodeVersion> getLatestVersion(UUID submissionId) {
        return versionRepository.findTopBySubmissionIdOrderByVersionNumberDesc(submissionId);
    }
}