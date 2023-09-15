package com.example.kodomoproject.domain.product.service.facade;

import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.exception.ProductNotFoundException;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductFacade {
    private final ProductRepository productRepository;

    public Product getProductById(String id) {
        return productRepository.findById(id)
                .orElseThrow(() -> ProductNotFoundException.EXCEPTION);
    }

}
