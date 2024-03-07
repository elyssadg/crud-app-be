package com.project.spring.boot.repository;

import com.project.spring.boot.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    public Mono<Product> findByName(String name);
}
