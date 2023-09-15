package com.example.kodomoproject.domain.product.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequest {
    private String title;

    private String content;

    private Integer price;

    private String image;

}
