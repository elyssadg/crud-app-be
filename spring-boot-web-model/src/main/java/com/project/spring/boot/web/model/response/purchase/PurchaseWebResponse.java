package com.project.spring.boot.web.model.response.purchase;

import com.project.spring.boot.entity.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseWebResponse implements Serializable {
    private Purchase purchase;
}
