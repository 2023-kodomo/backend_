package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.product.controller.dto.request.ProductUpdateRequest;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import com.example.kodomoproject.domain.product.service.facade.ProductFacade;
import com.example.kodomoproject.global.facade.DateFacade;
import com.example.kodomoproject.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductUpdateService {
    private final ProductRepository productRepository;
    private final ProductFacade productFacade;
    private final DateFacade dateFacade;
    private final S3Service s3Service;

    public void execute(ProductUpdateRequest request,
                           String id,
                           MultipartFile image) {
        Product product = productFacade.getProductById(id);
        Date now = dateFacade.getNowDate();

        String oldImage = product.getImageURL();

        if (!Objects.equals(oldImage, "")) {
            s3Service.deleteImageByURL(oldImage);
        }

        String imageURL = s3Service.uploadImage(image);

        product.update(
                request.getTitle(),
                request.getContent(),
                request.getPrice(),
                now,
                imageURL);

        productRepository.save(product);
    }

}
