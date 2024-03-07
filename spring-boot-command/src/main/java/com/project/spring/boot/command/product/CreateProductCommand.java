package com.project.spring.boot.command.product;

import com.project.spring.boot.command.model.request.product.CreateProductCommandRequest;
import com.project.spring.boot.web.model.response.product.ProductWebResponse;
import com.project.spring.boot.command.Command;

public interface CreateProductCommand extends Command<CreateProductCommandRequest, ProductWebResponse> {
}
