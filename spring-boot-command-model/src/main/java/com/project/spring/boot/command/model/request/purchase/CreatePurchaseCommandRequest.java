package com.project.spring.boot.command.model.request.purchase;

import com.project.spring.boot.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePurchaseCommandRequest {
    private Product product;
    private Integer totalPurchase;
}
