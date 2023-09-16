package com.example.kodomoproject.domain.comment.entity;

import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Document
public class Comment {
    @Id
    private String id;

    @NotBlank
    @DBRef
    private User writer;

    @NotBlank
    private String content;

    @NotBlank
    @DBRef
    private Product product;

    private Date createdDate;

    @Builder
    public Comment(User writer, String content, Product product, Date createdDate) {
        this.writer = writer;
        this.content = content;
        this.product = product;
        this.createdDate = createdDate;
    }

    public void update(String content, Date updatedDate) {
        this.content = content;
        this.createdDate = updatedDate;
    }

}
