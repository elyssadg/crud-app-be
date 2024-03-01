package com.project.spring.boot.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document("products")
public class Product {
    @Id
    private String id;

    private String name;

    private Integer stock;
}
