package com.example.kodomoproject.domain.comment.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentRequest {

    @NotBlank(message = "상품아이디가 존재하지 않습니다.")
    private String productId;

    @NotBlank(message = "댓글의 내용이 존재하지 않습니다.")
    private String content;

}
