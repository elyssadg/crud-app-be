package com.project.spring.boot.command.product;

import com.project.spring.boot.command.Command;
import com.project.spring.boot.command.model.request.product.DeleteProductCommandRequest;

public interface DeleteProductCommand extends Command<DeleteProductCommandRequest, Void> {
}
