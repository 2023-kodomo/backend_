package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.comment.service.CommentDeleteService;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import com.example.kodomoproject.domain.product.service.facade.ProductFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductDeleteService {
    private final ProductRepository productRepository;
    private final ProductFacade productFacade;
    private final CommentDeleteService commentDeleteService;

    public void execute(String id) {
        Product product = productFacade.getProductById(id);
        productRepository.deleteById(product.getId());
        commentDeleteService.deleteByProductId(id);
    }

}
