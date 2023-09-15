package com.example.kodomoproject.domain.product.exception;

import com.example.kodomoproject.global.exception.CustomException;
import com.example.kodomoproject.global.exception.error.ErrorCode;

public class ProductNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new ProductNotFoundException();

    private ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }
}
