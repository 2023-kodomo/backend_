package com.example.kodomoproject.domain.product.service;

import com.example.kodomoproject.domain.comment.service.CommentDeleteService;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.exception.UserNotMatchException;
import com.example.kodomoproject.domain.product.repository.ProductRepository;
import com.example.kodomoproject.domain.product.service.facade.ProductFacade;
import com.example.kodomoproject.domain.user.entity.User;
import com.example.kodomoproject.domain.user.service.facade.UserFacade;
import com.example.kodomoproject.global.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductDeleteService {
    private final ProductRepository productRepository;
    private final ProductFacade productFacade;
    private final UserFacade userFacade;
    private final CommentDeleteService commentDeleteService;
    private final S3Service s3Service;

    public void execute(String id) {
        User user = userFacade.getUser();
        Product product = productFacade.getProductById(id);

        if (product.getSeller() == user) {
            String imageURL = product.getImageURL();
            s3Service.deleteImageByURL(imageURL);
            productRepository.deleteById(product.getId());
            cascadeDeleteComment(id);
        } else {
            throw UserNotMatchException.EXCEPTION;
        }
    }

    private void cascadeDeleteComment(String id) {
        commentDeleteService.deleteByProductId(id);
    }

}
