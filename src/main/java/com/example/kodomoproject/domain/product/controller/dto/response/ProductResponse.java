package com.example.kodomoproject.domain.product.controller.dto.response;

import com.example.kodomoproject.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private String title;

    private String content;

    private User user;

    private Integer price;

    private String image;

    private Date uploadDate;

}
