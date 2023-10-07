package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.product.controller.dto.response.ProductDetailResponse;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFindByTitleService {
    private final ProductRepository productRepository;

    public List<ProductDetailResponse> execute(String input) {
        List<Product> products = productRepository.findByTitleContaining(input);

        return products.stream()
                .map(ProductDetailResponse::new)
                .toList();
    }

}
