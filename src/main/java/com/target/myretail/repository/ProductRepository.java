package com.target.myretail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.target.myretail.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

    public Product findByProductId(long productId);

}