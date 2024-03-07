package com.project.spring.boot.repository;

import com.project.spring.boot.entity.Purchase;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PurchaseRepository extends ReactiveMongoRepository<Purchase, String> {
}
