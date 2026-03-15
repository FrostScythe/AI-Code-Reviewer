package com.project.ai_code_review.model;

import java.util.UUID;

/**
 * Request body for POST /api/code/compare.
 */
public class CompareRequest {
    private UUID versionA;
    private UUID versionB;

    public UUID getVersionA() { return versionA; }
    public void setVersionA(UUID versionA) { this.versionA = versionA; }

    public UUID getVersionB() { return versionB; }
    public void setVersionB(UUID versionB) { this.versionB = versionB; }
}