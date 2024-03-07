package com.project.spring.boot.command.impl.product;

import com.project.spring.boot.command.model.request.product.GetProductByIdCommandRequest;
import com.project.spring.boot.command.product.GetProductByIdCommand;
import com.project.spring.boot.exception.DataNotFoundException;
import com.project.spring.boot.repository.ProductRepository;
import com.project.spring.boot.web.model.response.product.ProductWebResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Component
public class GetProductByIdCommandImpl implements GetProductByIdCommand {

    private final ProductRepository productRepository;

    @Override
    public Mono<ProductWebResponse> execute(GetProductByIdCommandRequest commandRequest) {
        return productRepository.findById(commandRequest.getId())
                .switchIfEmpty(Mono.error(new DataNotFoundException("Product with given id not found")))
                .map(product -> ProductWebResponse.builder()
                                                    .product(product)
                                                    .build())
                .doOnSuccess(response -> log.info("Found product {}", response.getProduct().getName()))
                .doOnError(response -> log.error("Product with id {} not found", commandRequest.getId()));
    }

}
