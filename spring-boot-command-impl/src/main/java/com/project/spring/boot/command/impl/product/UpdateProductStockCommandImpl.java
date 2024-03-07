package com.project.spring.boot.command.impl.product;

import com.project.spring.boot.command.model.request.product.UpdateProductStockCommandRequest;
import com.project.spring.boot.command.product.UpdateProductStockCommand;
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
public class UpdateProductStockCommandImpl implements UpdateProductStockCommand {

    private final ProductRepository productRepository;

    @Override
    public Mono<ProductWebResponse> execute(UpdateProductStockCommandRequest commandRequest) {
        return productRepository.findById(commandRequest.getProductId())
                                .switchIfEmpty(Mono.error(new DataNotFoundException("Product purchased not found")))
                                .map(product -> {
                                    product.setStock(product.getStock() + commandRequest.getTotalPurchase());
                                    return product;
                                })
                                .flatMap(productRepository::save)
                                .map(product -> ProductWebResponse.builder().product(product).build())
                                .doOnSuccess(response -> log.info("Successfully update product stock for {}", response.getProduct().getId()))
                                .doOnError(response -> log.error("Failed to update product stock for {}", commandRequest.getProductId()));
    }

}
