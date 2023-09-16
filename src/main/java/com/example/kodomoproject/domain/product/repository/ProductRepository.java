package com.example.kodomoproject.domain.product.repository;

import com.example.kodomoproject.domain.product.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(sort = "{uploadDate: -1}")
    List<Product> findBySellerId(String id);

}
