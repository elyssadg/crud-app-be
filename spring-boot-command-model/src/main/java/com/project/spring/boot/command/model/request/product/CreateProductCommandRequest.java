package com.project.spring.boot.command.model.request.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductCommandRequest {
    private String id;

    private String name;

    private Integer stock;
}
