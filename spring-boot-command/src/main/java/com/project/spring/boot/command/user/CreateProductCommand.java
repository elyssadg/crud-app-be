package com.project.spring.boot.command.user;

import com.project.spring.boot.command.model.request.CreateProductCommandRequest;
import com.project.spring.boot.web.model.response.ProductWebResponse;

public interface CreateProductCommand extends Command<CreateProductCommandRequest, ProductWebResponse> {
}
