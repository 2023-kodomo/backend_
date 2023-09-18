package com.example.kodomoproject.domain.product.controller.dto.response;

import com.example.kodomoproject.domain.product.entity.Product;
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

    public UserProductResponse(Product product) {
        this.title = product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.image = product.getImageURL();
        this.uploadDate = product.getUploadDate();
    }
}
