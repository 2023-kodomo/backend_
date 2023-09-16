package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.product.controller.dto.request.ProductUpdateRequest;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import com.example.kodomoproject.domain.product.service.facade.ProductFacade;
import com.example.kodomoproject.global.facade.DateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProductUpdateService {
    private final ProductRepository productRepository;
    private final ProductFacade productFacade;
    private final DateFacade dateFacade;

    public Product execute(ProductUpdateRequest request,
                           String id) {
        Product product = productFacade.getProductById(id);
        Date now = dateFacade.getNowDate();

        product.update(
                request.getTitle(),
                request.getContent(),
                request.getPrice(),
                now,
                request.getImage());

        return productRepository.save(product);
    }

}
