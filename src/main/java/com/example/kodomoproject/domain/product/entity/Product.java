package com.example.kodomoproject.domain.product.entity;

import com.example.kodomoproject.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
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

    private String image;

    @CreatedDate
    private Date createdDate;

}
