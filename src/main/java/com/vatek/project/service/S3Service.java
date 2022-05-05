package com.catdev.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
    String putData(String bucketName, String pathFile, MultipartFile file);
    void deleteData();
}