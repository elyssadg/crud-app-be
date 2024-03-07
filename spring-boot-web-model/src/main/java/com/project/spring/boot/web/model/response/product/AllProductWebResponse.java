package com.project.spring.boot.web.model.response.product;

import com.project.spring.boot.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllProductWebResponse implements Serializable {
    List<Product> products;
}
