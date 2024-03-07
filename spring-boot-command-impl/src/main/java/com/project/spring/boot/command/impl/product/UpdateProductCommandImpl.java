package com.project.spring.boot.command.impl.product;

import com.project.spring.boot.command.model.request.product.UpdateProductCommandRequest;
import com.project.spring.boot.command.product.UpdateProductCommand;
import com.project.spring.boot.entity.Product;
import com.project.spring.boot.exception.DataNotFoundException;
import com.project.spring.boot.repository.ProductRepository;
import com.project.spring.boot.web.model.response.product.ProductWebResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateProductCommandImpl implements UpdateProductCommand {

    private final ProductRepository productRepository;

    @Override
    public Mono<ProductWebResponse> execute(UpdateProductCommandRequest commandRequest) {
        return productRepository.findById(commandRequest.getId())
                                .switchIfEmpty(Mono.error(new DataNotFoundException("Product to be deleted not found")))
                                .map((Product product) -> {
                                    product.setName(commandRequest.getNewName());
                                    product.setStock(commandRequest.getNewStock());
                                    return product;
                                })
                                .flatMap(productRepository::save)
                                .map(product -> ProductWebResponse.builder().product(product).build())
                                .doOnSuccess(response -> log.info("Successfully updated product {}", commandRequest.getId()))
                                .doOnError(response -> log.error("Failed to update product {}", commandRequest.getId()));
    }

}
