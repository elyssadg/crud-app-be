package com.project.spring.boot.web.model.request.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductWebRequest {
    @NotEmpty(message = "Product name must not be empty")
    private String name;

    @NotNull(message = "Product stock must not be empty")
    @Min(value = 1, message = "Product stock must be greater than zero")
    private Integer stock;
}
