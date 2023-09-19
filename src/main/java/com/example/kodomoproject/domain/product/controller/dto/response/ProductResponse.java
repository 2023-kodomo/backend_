package com.example.kodomoproject.domain.product.controller.dto.response;

import com.example.kodomoproject.domain.comment.controller.dto.response.CommentResponse;
import com.example.kodomoproject.domain.product.entity.ProductDetails;
import com.example.kodomoproject.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String id;

    private User seller;

    private String title;

    private String content;

    private Integer price;

    private String image;

    private Date uploadDate;

    private List<CommentResponse> comment;

    @Builder
    public ProductResponse(String id, User seller, ProductDetails details,
                           List<CommentResponse> comment) {
        this.id = id;
        this.seller = seller;
        this.title = details.getTitle();
        this.content = details.getContent();
        this.price = details.getPrice();
        this.comment = comment;
        this.image = details.getImage();
        this.uploadDate = details.getUploadDate();
    }

}
