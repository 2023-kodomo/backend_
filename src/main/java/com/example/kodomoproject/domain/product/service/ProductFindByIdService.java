package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.product.controller.dto.response.ProductResponse;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.service.facade.ProductFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFindByIdService {
    private final ProductFacade productFacade;

    public ProductResponse execute(String id) {
        Product product = productFacade.getProductById(id);

        return ProductResponse.builder()
                .title(product.getTitle())
                .content(product.getContent())
                .user(product.getSeller())
                .price(product.getPrice())
                .image(product.getImage())
                .uploadDate(product.getUploadDate())
                .build();
    }

}
