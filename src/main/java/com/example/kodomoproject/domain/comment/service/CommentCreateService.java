package com.example.kodomoproject.domain.comment.service;

import com.example.kodomoproject.domain.comment.controller.dto.request.CommentRequest;
import com.example.kodomoproject.domain.comment.entity.Comment;
import com.example.kodomoproject.domain.comment.repository.CommentRepository;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import com.example.kodomoproject.domain.product.service.facade.ProductFacade;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import com.example.kodomoproject.global.facade.DateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CommentCreateService {
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final UserFacade userFacade;
    private final ProductFacade productFacade;
    private final DateFacade dateFacade;

    public void execute(CommentRequest request) {
        User user = userFacade.getUser();
        String productId = request.getProductId();
        Product product = productFacade.getProductById(productId);
        Date now = dateFacade.getNowDate();

        Comment comment = Comment.builder()
                .writer(user)
                .content(request.getContent())
                .product(product)
                .createdDate(now)
                .build();

        commentRepository.save(comment);
        addCommentToProduct(product, comment);
    }

    private void addCommentToProduct(Product product, Comment comment) {
        product.getComments().add(comment);
        productRepository.save(product);
    }

}
