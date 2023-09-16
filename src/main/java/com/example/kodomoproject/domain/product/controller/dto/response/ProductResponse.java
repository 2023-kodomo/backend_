package com.example.kodomoproject.domain.product.controller.dto.response;

import com.example.kodomoproject.domain.comment.controller.dto.response.CommentResponse;
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
    private User seller;

    private String title;

    private String content;

    private Integer price;

    private String image;

    private Date uploadDate;

    private List<CommentResponse> comment;

    @Builder
    public ProductResponse(User seller, String title, String content, Integer price,
                           List<CommentResponse> comment, String image, Date uploadDate) {
        this.seller = seller;
        this.title = title;
        this.content = content;
        this.price = price;
        this.comment = comment;
        this.image = image;
        this.uploadDate = uploadDate;
    }

}
