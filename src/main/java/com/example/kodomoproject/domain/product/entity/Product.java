package com.example.kodomoproject.domain.product.entity;

import com.example.kodomoproject.domain.comment.entity.Comment;
import com.example.kodomoproject.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@Document
public class Product {
    @Id
    private String id;

    @DBRef
    private User seller;

    private String title;

    private String content;

    private Integer price;

    private Date uploadDate;

    private String imageURL = "";

    @DBRef
    private List<Comment> comments = new ArrayList<>();

    private String place = "미정";

    @Builder
    public Product(User seller,String title, String content, Integer price, Date updatedDate, String image, String place) {
        this.seller = seller;
        this.title = title;
        this.content = content;
        this.price = price;
        this.uploadDate = updatedDate;
        this.imageURL = image;
        this.place = place;
    }

    public void update(String title, String content, Integer price, Date updatedDate, String image, String place) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.uploadDate = updatedDate;
        this.imageURL = image;
        this.place = place;
    }

}
