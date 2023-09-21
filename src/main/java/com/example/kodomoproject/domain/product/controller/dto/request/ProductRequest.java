package com.example.kodomoproject.domain.product.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    private String title;

    @NotNull
    private String content;

    @Min(0)
    @NotBlank
    private Integer price;

    private String image;

}
