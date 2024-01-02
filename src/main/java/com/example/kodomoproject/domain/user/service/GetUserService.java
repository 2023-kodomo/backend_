package com.example.kodomoproject.domain.user.service;

import com.example.kodomoproject.domain.product.controller.dto.response.ProductDetailResponse;
import com.example.kodomoproject.domain.product.service.ProductFindByUserService;
import com.example.kodomoproject.domain.qr.request.QrRequest;
import com.example.kodomoproject.domain.qr.service.QrGenerateService;
import com.example.kodomoproject.domain.user.controller.dto.response.UserResponse;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserService {
    private final ProductFindByUserService productFindByUserService;
    private final UserFacade userFacade;
    private final QrGenerateService qrGenerateService;

    @Value("${app.url}")
    private String defaultURL;

    public UserResponse myInfo() {
        User user = userFacade.getUser();
        List<ProductDetailResponse> products = productFindByUserService.execute(user.getId());

        return  UserResponse.builder()
                .id(user.getId())
                .profileImage(user.getProfileImage())
                .name(user.getName())
                .email(user.getEmail())
                .product(products)
                .build();
    }

    public UserResponse otherUser(String id) {
        User user = userFacade.getUserById(id);
        List<ProductDetailResponse> products = productFindByUserService.execute(user.getId());

        return  UserResponse.builder()
                .id(user.getId())
                .profileImage(user.getProfileImage())
                .name(user.getName())
                .email(user.getEmail())
                .product(products)
                .build();
    }

    public BufferedImage userQR() throws WriterException {
        User user = userFacade.getUser();

        String url = defaultURL + "/user" + user.getId();

        QrRequest qrRequest = new QrRequest(url);

        return qrGenerateService.execute(qrRequest);
    }

}
