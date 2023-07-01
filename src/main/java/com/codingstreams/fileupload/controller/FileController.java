package com.codingstreams.fileupload.controller;

import com.codingstreams.fileupload.dto.FileUploadResponse;
import com.codingstreams.fileupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            FileUploadResponse fileUploadResponse = fileService.uploadFile(file);
            return ResponseEntity.ok(fileUploadResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .build();
        }
    }

    @GetMapping("/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        var fileBytes = fileService.getFile(filename);

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileBytes);
    }
}
