package com.example.kodomoproject.global.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.kodomoproject.global.s3.exception.ImageDeleteFailedException;
import com.example.kodomoproject.global.s3.exception.ImageUploadFailedException;
import com.example.kodomoproject.global.s3.exception.ImageNotFoundException;
import com.example.kodomoproject.global.error.exception.InvalidDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile image) {
        String imageName = image.getOriginalFilename();
        String fileName = UUID.randomUUID() + imageName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        metadata.setContentType(image.getContentType());

        try {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, image.getInputStream(), metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw ImageUploadFailedException.EXCEPTION;
        }

        return getFileUrl(fileName);
    }

    public String getFileUrl(String imageName) {
        return amazonS3.getUrl(bucket, imageName).toString();
    }

//    public void deleteObject(String bucketName,
//                             String key )
    public void deleteImage(String imageName) {
        if (amazonS3.doesObjectExist(bucket, imageName)) {
            try {
                amazonS3.deleteObject(bucket, imageName);
            } catch (Exception e) {
                throw ImageDeleteFailedException.EXCEPTION;
            }
        } else {
            throw ImageNotFoundException.EXCEPTION;
        }
    }

    public void deleteImageByURL(String imageURL) {
        if (!Objects.equals(imageURL, "")) {
            try {
                String imageName = extractImageName(imageURL);
                deleteImage(imageName);
            } catch (Exception e) {
                throw InvalidDataException.EXCEPTION;
            }
        } else {
            throw ImageNotFoundException.EXCEPTION;
        }
    }

    private String extractImageName(String imageURL) {
        String splitStr = ".com/";
        return imageURL.substring(imageURL.lastIndexOf(splitStr) + splitStr.length());
    }

}
