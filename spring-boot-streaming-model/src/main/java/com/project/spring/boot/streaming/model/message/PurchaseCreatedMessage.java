package com.project.spring.boot.streaming.model.message;

import com.project.spring.boot.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCreatedMessage implements Serializable {
    private Product product;

    private Integer totalPurchase;
}
