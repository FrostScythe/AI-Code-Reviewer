package com.project.ai_code_review.model;

/**
 * Response body returned by POST /api/code/compare.
 * Contains the code of both requested versions side-by-side.
 */
public class CompareResponse {

    private VersionInfo versionA;
    private VersionInfo versionB;

    public CompareResponse(VersionInfo versionA, VersionInfo versionB) {
        this.versionA = versionA;
        this.versionB = versionB;
    }

    public VersionInfo getVersionA() { return versionA; }
    public VersionInfo getVersionB() { return versionB; }

    public static class VersionInfo {
        private int versionNumber;
        private String code;
        private String analysis;

        public VersionInfo(int versionNumber, String code, String analysis) {
            this.versionNumber = versionNumber;
            this.code = code;
            this.analysis = analysis;
        }

        public int getVersionNumber() { return versionNumber; }
        public String getCode()       { return code; }
        public String getAnalysis()   { return analysis; }
    }
}