package com.project.ai_code_review.service;

import com.project.ai_code_review.model.CodeSubmission;
import com.project.ai_code_review.model.CodeVersion;
import com.project.ai_code_review.repository.CodeSubmissionRepository;
import com.project.ai_code_review.repository.CodeVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeService {

    @Autowired
    private CodeSubmissionRepository codeSubmissionRepository;

    @Autowired
    private CodeVersionRepository codeVersionRepository;

    public CodeSubmission createCodeSubmission(CodeSubmission codeSubmission) {
        CodeSubmission saved = codeSubmissionRepository.save(codeSubmission);

        CodeVersion version = new CodeVersion();
        version.setSubmission(saved);
        version.setVersionNumber(1);
        version.setCode(saved.getCode());
        version.setAnalysis("Pending analysis...");
        codeVersionRepository.save(version);

        return saved;
    }

    public List<CodeSubmission> getSubmissionsByUser(String userId) {
        // ✅ FIX: use the injected instance 'codeSubmissionRepository', NOT the class name
        return codeSubmissionRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}