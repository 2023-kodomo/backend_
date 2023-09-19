package com.example.kodomoproject.domain.product.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ProductUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private Integer price;

    private String image;

}
