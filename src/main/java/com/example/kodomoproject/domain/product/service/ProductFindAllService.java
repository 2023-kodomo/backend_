package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.comment.controller.dto.response.CommentResponse;
import com.example.kodomoproject.domain.product.controller.dto.response.ProductResponse;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.entity.ProductDetails;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFindAllService {
    private final ProductRepository productRepository;

    public List<ProductResponse> execute() {
        List<Product> productList = productRepository.findAll();

        return productList.stream().map(p -> new ProductResponse(
                p.getId(),
                p.getSeller(),
                new ProductDetails(
                        p.getTitle(),
                        p.getContent(),
                        p.getPrice(),
                        p.getImageURL(),
                        p.getUploadDate()),
                p.getComments()
                        .stream()
                        .map(CommentResponse::new)
                        .toList()))
                .toList();
    }

}
