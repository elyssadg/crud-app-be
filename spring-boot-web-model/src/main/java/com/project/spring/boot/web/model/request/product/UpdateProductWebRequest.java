package com.project.spring.boot.web.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductWebRequest {
    private String id;

    private String newName;

    private Integer newStock;
}
