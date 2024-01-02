package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.image.service.ImageUploadService;
import com.example.kodomoproject.domain.product.controller.dto.request.ProductRequest;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import com.example.kodomoproject.global.facade.DateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProductCreateService {
    private final ProductRepository productRepository;
    private final UserFacade userFacade;
    private final DateFacade dateFacade;
    private final ImageUploadService imageUploadService;

    public void execute(ProductRequest request, MultipartFile image) {
        User user = userFacade.getUser();
        Date now = dateFacade.getNowDate();
        String imageURL = (image == null || image.isEmpty())
                ? ""
                : imageUploadService.execute(image);

        productRepository.save(Product.builder()
                .seller(user)
                .title(request.getTitle())
                .content(request.getContent())
                .price(request.getPrice())
                .image(imageURL)
                .updatedDate(now)
                .build());
    }

}
