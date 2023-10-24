package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.product.collection.ProductCollection;
import com.example.kodomoproject.domain.product.controller.dto.response.ProductDetailResponse;
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

    public List<ProductDetailResponse> execute() {
        List<Product> productList = productRepository.findAll();
        ProductCollection productCollection = new ProductCollection(productList);

        return productCollection.toProductDetailResponses();
    }

}
