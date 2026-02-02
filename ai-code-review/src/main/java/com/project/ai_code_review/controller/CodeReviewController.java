package com.project.ai_code_review.controller;


import com.project.ai_code_review.model.CodeSubmission;
import com.project.ai_code_review.model.CodeVersion;
import com.project.ai_code_review.service.CodeService;
import com.project.ai_code_review.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/code")
public class CodeReviewController {
    @Autowired
    private CodeService codeService;

    @Autowired
    private VersionService versionService;

    @GetMapping("/review")
    public String reviewCode() {
        return "Code review endpoint is working!";
    }

    @PostMapping("/upload")
    public ResponseEntity<CodeSubmission> uploadCode(@RequestBody CodeSubmission codeSubmission) {
        CodeSubmission saved = codeService.createCodeSubmission(codeSubmission);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/analyze/{submissionId}")
    public ResponseEntity<CodeVersion> analyzedCode(@PathVariable UUID submissionId) {
        Optional<CodeVersion> latest = versionService.getVersion(submissionId).stream().max(Comparator.comparingInt(CodeVersion::getVersionNumber));

        if(latest.isPresent()) {
            return ResponseEntity.ok(versionService.analyzeCodeVersion(latest.get().getId()));
        }

        return ResponseEntity.notFound().build();
    }
}