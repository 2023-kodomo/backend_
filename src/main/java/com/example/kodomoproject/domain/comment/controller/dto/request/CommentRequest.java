package com.example.kodomoproject.domain.comment.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "상품아이디가 존재하지 않습니다.")
    private String productId;

    @NotBlank
    private String content;

}
