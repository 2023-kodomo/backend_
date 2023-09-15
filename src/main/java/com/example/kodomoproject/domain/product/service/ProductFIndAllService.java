package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.product.controller.dto.response.ProductResponse;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFIndAllService {
    private final ProductRepository productRepository;

    public List<ProductResponse> execute() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(p -> new ProductResponse(
                p.getTitle(),
                p.getContent(),
                p.getSeller(),
                p.getPrice(),
                p.getImage(),
                p.getUploadDate()
        )).toList();
    }

}
