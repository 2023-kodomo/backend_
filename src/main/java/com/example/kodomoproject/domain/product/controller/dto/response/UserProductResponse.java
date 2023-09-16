package com.example.kodomoproject.domain.product.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserProductResponse {
    private String title;
    private String content;
    private Integer price;
    private String image;
    private Date uploadDate;
}
