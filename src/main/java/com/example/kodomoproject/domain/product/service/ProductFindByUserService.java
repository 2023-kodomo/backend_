package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.product.controller.dto.response.ProductDetailResponse;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductFindByUserService {
    private final ProductRepository productRepository;
    private final UserFacade userFacade;

    public List<ProductDetailResponse> execute(String id) {
        User user = userFacade.getUserById(id);
        List<Product> products = productRepository.findBySellerId(user.getId());

        return products.stream()
                .map(ProductDetailResponse::new)
                .toList();
    }
}
