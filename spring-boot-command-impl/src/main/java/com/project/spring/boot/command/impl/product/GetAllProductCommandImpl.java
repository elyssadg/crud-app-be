package com.project.spring.boot.command.impl.product;

import com.project.spring.boot.command.model.request.product.GetAllProductCommandRequest;
import com.project.spring.boot.command.product.GetAllProductCommand;
import com.project.spring.boot.repository.ProductRepository;
import com.project.spring.boot.web.model.response.product.AllProductWebResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetAllProductCommandImpl implements GetAllProductCommand {

    private final ProductRepository productRepository;

    @Override
    public Mono<AllProductWebResponse> execute(GetAllProductCommandRequest commandRequest) {
        return productRepository.findAll()
                                .collectList()
                                .map(products -> AllProductWebResponse.builder().products(products).build())
                                .doOnSuccess(response -> log.info("Successfully get all products"));
    }
}
