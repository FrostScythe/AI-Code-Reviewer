package com.project.ai_code_review.controller;

import com.project.ai_code_review.model.*;
import com.project.ai_code_review.repository.CodeVersionRepository;
import com.project.ai_code_review.service.CodeService;
import com.project.ai_code_review.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/code")
public class CodeReviewController {

    @Autowired
    private CodeService codeService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private CodeVersionRepository codeVersionRepository;

    // ─── Health check ────────────────────────────────────────────────────────

    @GetMapping("/review")
    public String reviewCode() {
        return "Code review endpoint is working!";
    }

    // ─── Upload ───────────────────────────────────────────────────────────────

    /**
     * POST /api/code/upload
     * Body: { userId, fileName, language, code }
     * Creates a new submission + auto Version 1.
     */
    @PostMapping("/upload")
    public ResponseEntity<CodeSubmission> uploadCode(@RequestBody CodeSubmission codeSubmission) {
        CodeSubmission saved = codeService.createCodeSubmission(codeSubmission);
        return ResponseEntity.ok(saved);
    }

    // ─── Analyze ─────────────────────────────────────────────────────────────

    /**
     * POST /api/code/analyze/{submissionId}
     * Finds the latest version of the submission and runs AI analysis on it.
     */
    @PostMapping("/analyze/{submissionId}")
    public ResponseEntity<CodeVersion> analyzeCode(@PathVariable UUID submissionId) {
        Optional<CodeVersion> latest = versionService.getLatestVersion(submissionId);
        if (latest.isPresent()) {
            return ResponseEntity.ok(versionService.analyzeVersion(latest.get().getId()));
        }
        return ResponseEntity.notFound().build();
    }

    // ─── Versions ────────────────────────────────────────────────────────────

    /**
     * GET /api/code/version/{submissionId}
     * Returns all versions for a submission, newest first.
     * Used by the frontend Review and Compare pages.
     */
    @GetMapping("/version/{submissionId}")
    public ResponseEntity<List<CodeVersion>> getVersions(@PathVariable UUID submissionId) {
        List<CodeVersion> versions = versionService.getVersions(submissionId);
        return ResponseEntity.ok(versions);
    }

    /**
     * POST /api/code/version/{submissionId}/new
     * Body: raw code string (text/plain)
     * Creates a new version under the submission and runs AI analysis immediately.
     */
    @PostMapping("/version/{submissionId}/new")
    public ResponseEntity<CodeVersion> createNewVersion(
            @PathVariable UUID submissionId,
            @RequestBody String code) {
        CodeVersion newVersion = versionService.createNewVersion(submissionId, code);
        return ResponseEntity.ok(newVersion);
    }

    // ─── User submissions ─────────────────────────────────────────────────────

    /**
     * GET /api/code/submissions/{userId}
     * Returns all submissions for a user, newest first.
     * Used by the History page.
     */
    @GetMapping("/submissions/{userId}")
    public ResponseEntity<List<CodeSubmission>> getUserSubmissions(@PathVariable String userId) {
        return ResponseEntity.ok(codeService.getSubmissionsByUser(userId));
    }

    // ─── Compare ─────────────────────────────────────────────────────────────

    /**
     * POST /api/code/compare
     * Body: { versionA: uuid, versionB: uuid }
     * Returns the code + analysis for both versions side-by-side.
     * Used by the Compare page.
     */
    @PostMapping("/compare")
    public ResponseEntity<CompareResponse> compareVersions(@RequestBody CompareRequest request) {
        CodeVersion vA = codeVersionRepository.findById(request.getVersionA())
                .orElseThrow(() -> new RuntimeException("Version A not found"));
        CodeVersion vB = codeVersionRepository.findById(request.getVersionB())
                .orElseThrow(() -> new RuntimeException("Version B not found"));

        CompareResponse response = new CompareResponse(
                new CompareResponse.VersionInfo(vA.getVersionNumber(), vA.getCode(), vA.getAnalysis()),
                new CompareResponse.VersionInfo(vB.getVersionNumber(), vB.getCode(), vB.getAnalysis())
        );
        return ResponseEntity.ok(response);
    }
}