package com.example.kodomoproject.domain.user.controller.dto;

import com.example.kodomoproject.domain.product.controller.dto.response.ProductResponse;
import com.example.kodomoproject.domain.product.controller.dto.response.UserProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;

    private String name;

    private String email;

    private List<UserProductResponse> product;

}
