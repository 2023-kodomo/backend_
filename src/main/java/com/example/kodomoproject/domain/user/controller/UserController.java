package com.example.kodomoproject.domain.user.controller;

import com.example.kodomoproject.domain.user.controller.dto.response.UserResponse;
import com.example.kodomoproject.domain.user.service.CustomProfileService;
import com.example.kodomoproject.domain.user.service.GetUserService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final GetUserService getUserService;
    private final CustomProfileService customProfileService;

    @GetMapping("/my-page")
    public UserResponse myPage() {
        return getUserService.myInfo();
    }

    @GetMapping("{id}")
    public UserResponse getOtherUserInfo(@PathVariable String id) {
        return getUserService.otherUser(id);
    }

    @PutMapping("/my-page/custom")
    public void customProfileImage(@RequestPart(required = false, value = "image")
                                       MultipartFile image) {
        customProfileService.execute(image);
    }

    @GetMapping(value = "/my-qr",
                produces = MediaType.IMAGE_PNG_VALUE)
    public BufferedImage getMyQR() throws WriterException {
        return getUserService.userQR();
    }

}