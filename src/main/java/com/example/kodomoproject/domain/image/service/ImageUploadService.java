package com.example.kodomoproject.domain.image.service;

import com.example.kodomoproject.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageUploadService {
    private final S3Service s3Service;

    public String execute(MultipartFile image) {

        return s3Service.uploadImage(image);
    }

}
