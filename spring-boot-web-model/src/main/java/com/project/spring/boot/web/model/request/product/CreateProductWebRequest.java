package com.project.spring.boot.web.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductWebRequest {
    @NotBlank(message = "Product ID must not be empty")
    private String id;

    @NotBlank(message = "Product name must not be empty")
    private String name;

    @NotBlank(message = "Product stock must not be empty")
    private Integer stock;
}
