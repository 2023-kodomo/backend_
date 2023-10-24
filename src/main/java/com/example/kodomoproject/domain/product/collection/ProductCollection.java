package com.example.kodomoproject.domain.product.collection;

import com.example.kodomoproject.domain.product.controller.dto.response.ProductDetailResponse;
import com.example.kodomoproject.domain.product.entity.Product;

import java.util.List;

public class ProductCollection {
    private final List<Product> products;

    public ProductCollection(List<Product> products) {
        this.products = products;
    }

    public List<ProductDetailResponse> toProductDetailResponses() {
        return products.stream()
                .map(ProductDetailResponse::new)
                .toList();
    }
}