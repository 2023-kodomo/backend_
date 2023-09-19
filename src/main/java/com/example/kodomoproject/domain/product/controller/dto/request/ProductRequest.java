package com.example.kodomoproject.domain.product.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    private String title;

    @NotNull
    private String content;

    @NotBlank
    private Integer price;

    private String image;

}
