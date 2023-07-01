package com.codingstreams.fileupload.service;

import com.codingstreams.fileupload.dto.FileUploadResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileUploadResponse uploadFile(MultipartFile file);

    byte[] getFile(String filename);
}
