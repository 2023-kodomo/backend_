package com.example.kodomoproject.domain.product.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class ProductDetails {
    private String title;

    private String content;

    private Integer price;

    private String image;

    private Date uploadDate;

    @Builder
    public ProductDetails(String title, String content, Integer price,
                          String image, Date uploadDate) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.image = image;
        this.uploadDate = uploadDate;
    }

}
