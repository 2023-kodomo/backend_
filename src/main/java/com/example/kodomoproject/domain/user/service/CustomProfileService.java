package com.example.kodomoproject.domain.user.service;

import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import com.example.kodomoproject.global.s3.DefaultProfile;
import com.example.kodomoproject.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomProfileService {
    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final S3Service s3Service;

    public void execute(MultipartFile image) {
        User user = userFacade.getUser();
        String profileImage = user.getProfileImage();

        if (!Objects.equals(profileImage, DefaultProfile.DEFAULT_PROFILE)) {
            s3Service.deleteImageByURL(profileImage);
        }

        user.imageUpload(s3Service.uploadImage(image));

        userRepository.save(user);
    }

}
