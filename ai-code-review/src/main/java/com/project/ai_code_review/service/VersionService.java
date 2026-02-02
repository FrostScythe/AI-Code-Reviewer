package com.project.ai_code_review.service;

import com.project.ai_code_review.model.CodeVersion;
import com.project.ai_code_review.repository.CodeVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class VersionService {
    @Autowired
    private CodeVersionRepository versionRepository;

    @Autowired
    private AiService aiService;

    public List<CodeVersion> getVersion(UUID submissionId) {
        // Implementation goes here
        return versionRepository.findBySubmissionId(submissionId);
    }


    public CodeVersion analyzeCodeVersion(UUID versionId){
        // Implementation goes here
        CodeVersion version =  versionRepository.findById(versionId)
                .orElseThrow(()-> new RuntimeException("Version not found"));

        String analysis= aiService.analyzeCode(version.getCode());
        version.setAnalysis(analysis);

        return versionRepository.save(version);
    }

}