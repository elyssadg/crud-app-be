package com.project.spring.boot.web.model.response;

import com.project.spring.boot.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AllProductWebResponse {
    List<Product> products;
}
