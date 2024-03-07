package com.project.spring.boot.command.product;

import com.project.spring.boot.command.Command;
import com.project.spring.boot.command.model.request.product.UpdateProductStockCommandRequest;
import com.project.spring.boot.web.model.response.product.ProductWebResponse;

public interface UpdateProductStockCommand extends Command<UpdateProductStockCommandRequest, ProductWebResponse> {
}
