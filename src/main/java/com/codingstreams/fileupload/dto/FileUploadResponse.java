package com.codingstreams.fileupload.dto;

public class FileUploadResponse {
    private final String filename;
    private final String downloadUrl;

    public FileUploadResponse(String filename, String downloadUrl) {
        this.filename = filename;
        this.downloadUrl = downloadUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public String getFilename() {
        return filename;
    }
}
