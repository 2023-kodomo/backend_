package com.example.kodomoproject.domain.user.controller.dto.response;

import com.example.kodomoproject.domain.product.controller.dto.response.ProductDetailResponse;
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

    private String profileImage;

    private String name;

    private String email;

    private List<ProductDetailResponse> product;

}
