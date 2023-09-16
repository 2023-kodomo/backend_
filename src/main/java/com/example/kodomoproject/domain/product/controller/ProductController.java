package com.example.kodomoproject.domain.product.controller;

import com.example.kodomoproject.domain.product.controller.dto.request.ProductRequest;
import com.example.kodomoproject.domain.product.controller.dto.request.ProductUpdateRequest;
import com.example.kodomoproject.domain.product.controller.dto.response.ProductResponse;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductCreateService productCreateService;
    private final ProductUpdateService productModifyService;
    private final ProductDeleteService productDeleteService;
    private final ProductFindAllService productFindAllService;
    private final ProductFindByIdService productFindByIdService;

    @PostMapping
    public void createProduct(@RequestBody ProductRequest request) {
        productCreateService.execute(request);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@RequestBody ProductUpdateRequest request,
                                 @PathVariable String id) {
        return productModifyService.execute(request, id);
    }

    @GetMapping("/view/{id}")
    public ProductResponse findById(@PathVariable String id) {
        return productFindByIdService.execute(id);
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return productFindAllService.execute();
    }

    @DeleteMapping("{id}")
    public void deleteProductById(@PathVariable String id) {
        productDeleteService.execute(id);
    }


}
