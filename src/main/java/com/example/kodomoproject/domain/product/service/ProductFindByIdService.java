package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.comment.controller.dto.response.CommentResponse;
import com.example.kodomoproject.domain.product.controller.dto.response.ProductResponse;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.entity.ProductDetails;
import com.example.kodomoproject.domain.product.service.facade.ProductFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductFindByIdService {
    private final ProductFacade productFacade;

    public ProductResponse execute(String id) {
        Product product = productFacade.getProductById(id);

        ProductDetails details = ProductDetails.builder()
                .title(product.getTitle())
                .content(product.getContent())
                .price(product.getPrice())
                .image(product.getImageURL())
                .uploadDate(product.getUploadDate())
                .build();

        return ProductResponse.builder()
                .seller(product.getSeller())
                .details(details)
                .place(product.getPlace())
                .comment(product.getComments().stream()
                        .map(CommentResponse::new)
                        .toList())
                .build();
    }
}
