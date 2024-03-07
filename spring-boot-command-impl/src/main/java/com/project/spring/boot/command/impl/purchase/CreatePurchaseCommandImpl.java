package com.project.spring.boot.command.impl.purchase;

import com.project.spring.boot.command.model.request.purchase.CreatePurchaseCommandRequest;
import com.project.spring.boot.command.purchase.CreatePurchaseCommand;
import com.project.spring.boot.entity.Purchase;
import com.project.spring.boot.repository.PurchaseRepository;
import com.project.spring.boot.web.model.response.purchase.PurchaseWebResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreatePurchaseCommandImpl implements CreatePurchaseCommand {

    private final PurchaseRepository purchaseRepository;

    @Override
    public Mono<PurchaseWebResponse> execute(CreatePurchaseCommandRequest commandRequest) {
        return Mono.fromCallable(() -> Purchase.builder()
                                                .product(commandRequest.getProduct())
                                                .totalPurchase(commandRequest.getTotalPurchase())
                                                .build())
                        .flatMap(purchaseRepository::save)
                        .map(purchase -> PurchaseWebResponse.builder()
                                                            .purchase(purchase)
                                                            .build())
                        .doOnSuccess(response -> log.info("Successfully purchased {} items of product {}", response.getPurchase().getTotalPurchase(), response.getPurchase().getProduct().getName()));
    }

}
