package com.project.ai_code_review.service;


import com.project.ai_code_review.model.CodeSubmission;
import com.project.ai_code_review.repository.CodeSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeService {

    @Autowired
    private CodeSubmissionRepository codeSubmissionRepository;

    public CodeSubmission createCodeSubmission(CodeSubmission codeSubmission) {
        CodeSubmission savedSubmission = codeSubmissionRepository.save(codeSubmission);
        return savedSubmission;
    }
}
