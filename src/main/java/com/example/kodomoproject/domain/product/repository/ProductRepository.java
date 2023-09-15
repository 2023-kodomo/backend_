package com.example.kodomoproject.domain.product.repository;

import com.example.kodomoproject.domain.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}