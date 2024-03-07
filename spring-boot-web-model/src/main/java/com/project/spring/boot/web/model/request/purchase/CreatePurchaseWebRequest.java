package com.project.spring.boot.web.model.request.purchase;

import com.project.spring.boot.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePurchaseWebRequest {
    @NotBlank(message = "Product id to purchase must not be empty")
    private String productId;

    @NotNull(message = "Total product to be purchased must not be empty")
    @Min(value = 1, message = "Total product to be purchased must be greater than zero")
    private Integer totalPurchase;
}
