package com.project.ai_code_review.service;

import com.project.ai_code_review.model.CodeSubmission;
import com.project.ai_code_review.model.CodeVersion;
import com.project.ai_code_review.repository.CodeSubmissionRepository;
import com.project.ai_code_review.repository.CodeVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CodeService {

    @Autowired
    private CodeSubmissionRepository codeSubmissionRepository;

    @Autowired
    private CodeVersionRepository codeVersionRepository;

    /**
     * Save a new code submission and auto-create Version 1.
     */
    public CodeSubmission createCodeSubmission(CodeSubmission codeSubmission) {
        CodeSubmission saved = codeSubmissionRepository.save(codeSubmission);

        // Always create Version 1 automatically on first upload
        CodeVersion version = new CodeVersion();
        version.setSubmission(saved);
        version.setVersionNumber(1);
        version.setCode(saved.getCode());
        version.setAnalysis("Pending analysis...");
        codeVersionRepository.save(version);

        return saved;
    }

    /**
     * Fetch all submissions belonging to a specific user, newest first.
     */
    public List<CodeSubmission> getSubmissionsByUser(String userId) {
        return CodeSubmissionRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}