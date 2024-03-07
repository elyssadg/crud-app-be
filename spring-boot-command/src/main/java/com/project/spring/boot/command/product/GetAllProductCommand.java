package com.project.spring.boot.command.product;

import com.project.spring.boot.command.Command;
import com.project.spring.boot.command.model.request.product.GetAllProductCommandRequest;
import com.project.spring.boot.web.model.response.product.AllProductWebResponse;

public interface GetAllProductCommand extends Command<GetAllProductCommandRequest, AllProductWebResponse> {
}
