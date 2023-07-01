package com.codingstreams.fileupload.service;

import com.codingstreams.fileupload.dto.FileUploadResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload-path}")
    private String uploadPath;

    @Override
    public FileUploadResponse uploadFile(MultipartFile file) {
        // Check if file null or not
        if (file == null) {
            throw new RuntimeException("File is null");
        }

        try {
            // Create upload folder if not exists
            if (!Files.exists(Path.of(uploadPath))) {
                Files.createDirectories(Path.of(uploadPath));
            }

            // Get original file name
            String originalFilename = file.getOriginalFilename();

            // Get file extension
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

            // Create file name
            String filename = UUID.randomUUID().toString() + fileExtension;

            // Create target path
            String targetPath = this.uploadPath + "/" + filename;

            // Save file
            Files.copy(file.getInputStream(), Path.of(targetPath));

            return new FileUploadResponse(filename, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public byte[] getFile(String filename) {
        try (var file = new FileInputStream(new File(Path.of(uploadPath, filename).toUri()))) {
            return file.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
