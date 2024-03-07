package com.project.spring.boot.command.impl.product;

import com.project.spring.boot.command.model.request.product.DeleteProductCommandRequest;
import com.project.spring.boot.command.product.DeleteProductCommand;
import com.project.spring.boot.exception.DataNotFoundException;
import com.project.spring.boot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteProductCommandImpl implements DeleteProductCommand {

    private final ProductRepository productRepository;

    @Override
    public Mono<Void> execute(DeleteProductCommandRequest commandRequest) {
        return productRepository.deleteById(commandRequest.getId())
                                .doOnSuccess(response -> log.info("Successfully delete product {}", commandRequest.getId()))
                                .doOnError(response -> log.error("Failed to delete product {}", commandRequest.getId()));
    }

}
