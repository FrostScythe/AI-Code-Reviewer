package com.project.ai_code_review.service;


import com.project.ai_code_review.model.CodeSubmission;
import com.project.ai_code_review.model.CodeVersion;
import com.project.ai_code_review.repository.CodeSubmissionRepository;
import com.project.ai_code_review.repository.CodeVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    @Autowired
    private CodeSubmissionRepository codeSubmissionRepository;

    @Autowired
    private CodeVersionRepository codeVersionRepository;  // ← inject this

    public CodeSubmission createCodeSubmission(CodeSubmission codeSubmission) {
        CodeSubmission saved = codeSubmissionRepository.save(codeSubmission);

        // create version 1 automatically
        CodeVersion version = new CodeVersion();
        version.setSubmission(saved);
        version.setVersionNumber(1);
        version.setCode(saved.getCode());
        version.setAnalysis("Pending analysis...");
        codeVersionRepository.save(version);

        return saved;
    }
}
