package com.project.spring.boot.web;

import com.project.spring.boot.command.impl.executor.CommandExecutor;
import com.project.spring.boot.command.model.request.purchase.CreatePurchaseCommandRequest;
import com.project.spring.boot.command.model.request.product.GetProductByIdCommandRequest;
import com.project.spring.boot.command.product.GetProductByIdCommand;
import com.project.spring.boot.command.purchase.CreatePurchaseCommand;
import com.project.spring.boot.streaming.Producer;
import com.project.spring.boot.streaming.model.message.PurchaseCreatedMessage;
import com.project.spring.boot.web.model.request.purchase.CreatePurchaseWebRequest;
import com.project.spring.boot.web.model.response.base.BaseResponse;
import com.project.spring.boot.web.model.response.product.ProductWebResponse;
import com.project.spring.boot.web.model.response.purchase.PurchaseWebResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/backend/purchase")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class PurchaseController {

    private final Producer producer;

    private final CommandExecutor commandExecutor;

    @PostMapping()
    @CacheEvict(cacheNames = "product", key = "'products'")
    public Mono<BaseResponse<PurchaseWebResponse>> purchaseProduct(@Valid @RequestBody CreatePurchaseWebRequest request) {
        return commandExecutor.execute(GetProductByIdCommand.class,
                                        GetProductByIdCommandRequest.builder()
                                                .id(request.getProductId())
                                                .build())
                    .map(ProductWebResponse::getProduct)
                    .flatMap(product -> commandExecutor.execute(CreatePurchaseCommand.class,
                                                                CreatePurchaseCommandRequest.builder()
                                                                                            .product(product)
                                                                                            .totalPurchase(request.getTotalPurchase())
                                                                                            .build()))
                    .map(purchaseWebResponse -> {
                            producer.sendMessage("purchase", PurchaseCreatedMessage.builder()
                                                                                        .product(purchaseWebResponse.getPurchase().getProduct())
                                                                                        .totalPurchase(purchaseWebResponse.getPurchase().getTotalPurchase())
                                                                                        .build());
                        return purchaseWebResponse;
                    })
                    .map(BaseResponse::ok);
    }

}
