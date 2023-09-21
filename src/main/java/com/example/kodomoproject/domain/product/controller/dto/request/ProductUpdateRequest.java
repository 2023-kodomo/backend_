package com.example.kodomoproject.domain.product.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class ProductUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @Min(0)
    @Max(5000000)
    @NotBlank
    private Integer price;

    private String image;

}
