package com.example.kodomoproject.domain.comment.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class CommentRequest {
    private String productId;

    private String content;

}
