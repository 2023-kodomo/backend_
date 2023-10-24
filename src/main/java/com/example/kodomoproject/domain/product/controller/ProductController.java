package com.example.kodomoproject.domain.product.controller;

import com.example.kodomoproject.domain.product.controller.dto.request.ProductRequest;
import com.example.kodomoproject.domain.product.controller.dto.request.ProductUpdateRequest;
import com.example.kodomoproject.domain.product.controller.dto.response.ProductResponse;
import com.example.kodomoproject.domain.product.controller.dto.response.ProductDetailResponse;
import com.example.kodomoproject.domain.product.entity.Product;
import com.example.kodomoproject.domain.product.entity.ProductDetails;
import com.example.kodomoproject.domain.product.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductCreateService productCreateService;
    private final ProductUpdateService productModifyService;
    private final ProductDeleteService productDeleteService;
    private final ProductFindAllService productFindAllService;
    private final ProductFindByIdService productFindByIdService;
    private final ProductFindByTitleService productFindByTitleService;

    @PostMapping
    public void createProduct(@RequestParam("title") String title,
                              @RequestParam("content") String content,
                              @RequestParam("price") int price,
                              @RequestParam("place") String place,
                              @RequestPart(required = false, value = "image") MultipartFile image) {
        ProductRequest request = new ProductRequest(title, content, price, place);

        productCreateService.execute(request, image);
    }

    @PutMapping("/{productId}")
    public Product updateProduct(@PathVariable String productId,
                                 @RequestParam("title") String title,
                                 @RequestParam("content") String content,
                                 @RequestParam("price") int price,
                                 @RequestParam("place") String place,
                                 @RequestPart(required = false, value = "image") MultipartFile image) {
        ProductUpdateRequest request = new ProductUpdateRequest(title, content, price, place);

        return productModifyService.execute(request, productId, image);
    }

    @GetMapping("/view/{productId}")
    public ProductResponse findById(@PathVariable String productId) {
        return productFindByIdService.execute(productId);
    }

    @GetMapping
    public List<ProductDetailResponse> findAll() {
        return productFindAllService.execute();
    }

    @GetMapping("/search")
    public List<ProductDetailResponse> findByTitle(@RequestParam String input) {
        return productFindByTitleService.execute(input);
    }

    @DeleteMapping("{productId}")
    public void deleteProductById(@PathVariable String productId) {
        productDeleteService.execute(productId);
    }


}
