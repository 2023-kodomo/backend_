package com.example.kodomoproject.domain.user.service;

import com.example.kodomoproject.domain.product.controller.dto.response.UserProductResponse;
import com.example.kodomoproject.domain.product.service.ProductFindByUserService;
import com.example.kodomoproject.domain.user.controller.dto.UserResponse;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.repository.UserRepository;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserService {
    private final ProductFindByUserService productFindByUserService;
    private final UserFacade userFacade;

    public UserResponse myInfo() {
        User user = userFacade.getUser();
        List<UserProductResponse> products = productFindByUserService.execute(user.getId());

        return  UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .product(products)
                .build();
    }

    public UserResponse otherUser(String id) {
        User user = userFacade.getUserById(id);
        List<UserProductResponse> products = productFindByUserService.execute(user.getId());

        return  UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .product(products)
                .build();
    }

}
