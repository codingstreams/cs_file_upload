package com.codingstreams.fileupload.controller;

import com.codingstreams.fileupload.dto.FileUploadResponse;
import com.codingstreams.fileupload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private FileService fileService;

    @PostMapping("/")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        try {
            FileUploadResponse fileUploadResponse = fileService.uploadFile(file);
            return ResponseEntity.ok(fileUploadResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .build();
        }
    }
}
