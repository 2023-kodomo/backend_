package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.product.controller.dto.request.ProductRequest;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import com.example.kodomoproject.domain.product.service.facade.ProductFacade;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import com.example.kodomoproject.global.facade.DateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class ProductCreateService {
    private final ProductRepository productRepository;
    private final UserFacade userFacade;
    private final DateFacade dateFacade;

    public void execute(ProductRequest request) {
        User user = userFacade.getUser();
        Date now = dateFacade.getNowDate();

        productRepository.save(Product.builder()
                .seller(user)
                .title(request.getTitle())
                .content(request.getContent())
                .price(request.getPrice())
                .image(request.getImage())
                .uploadDate(now)
                .build());
    }

}
