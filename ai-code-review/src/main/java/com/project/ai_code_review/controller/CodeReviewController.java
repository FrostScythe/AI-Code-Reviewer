package com.project.ai_code_review.controller;


import com.project.ai_code_review.model.CodeSubmission;
import com.project.ai_code_review.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/code")
public class CodeReviewController {
    @Autowired
    private CodeService codeService;

    @GetMapping("/review")
    public String reviewCode() {
        return "Code review endpoint is working!";
    }

    @PostMapping("/upload")
    public ResponseEntity<CodeSubmission> uploadCode(@RequestBody CodeSubmission codeSubmission) {
        CodeSubmission saved = codeService.createCodeSubmission(codeSubmission);
        return ResponseEntity.ok(saved);
    }
}
