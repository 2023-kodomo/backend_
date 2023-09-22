package com.example.kodomoproject.domain.product.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "제목이 비어있습니다.")
    private String title;

    @NotBlank(message = "내용이 비어있습니다.")
    private String content;

    @NotNull
    @PositiveOrZero(message = "가격은 0 또는 양수만 입력가능합니다.")
    private Integer price;

    private String image;

}
