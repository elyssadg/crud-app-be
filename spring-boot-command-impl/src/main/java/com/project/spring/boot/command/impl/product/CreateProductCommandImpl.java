package com.project.spring.boot.command.impl.product;

import com.project.spring.boot.command.model.request.product.CreateProductCommandRequest;
import com.project.spring.boot.command.product.CreateProductCommand;
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
public class CreateProductCommandImpl implements CreateProductCommand {

    private final ProductRepository productRepository;

    @Override
    public Mono<ProductWebResponse> execute(CreateProductCommandRequest commandRequest) {

        return productRepository.findByName(commandRequest.getName())
                                .flatMap(product -> {
                                    product.setStock(product.getStock() + commandRequest.getStock());
                                    return productRepository.save(product);
                                })
                                .switchIfEmpty(Mono.fromCallable(() -> Product.builder()
                                                                                .name(commandRequest.getName())
                                                                                .stock(commandRequest.getStock())
                                                                                .build()
                                ).flatMap(productRepository::save))
                                .map(product -> ProductWebResponse.builder().product(product).build())
                                .doOnSuccess(response -> log.info("Successfully created new product {} with stock {}", response.getProduct().getName(), response.getProduct().getStock()));
    }

}
