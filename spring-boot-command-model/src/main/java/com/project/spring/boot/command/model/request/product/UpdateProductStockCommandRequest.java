package com.project.spring.boot.command.model.request.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductStockCommandRequest {
    private String productId;
    private Integer totalPurchase;
}
