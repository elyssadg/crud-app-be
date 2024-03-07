package com.project.spring.boot.streaming;

import com.project.spring.boot.command.impl.executor.CommandExecutor;
import com.project.spring.boot.command.model.request.product.UpdateProductStockCommandRequest;
import com.project.spring.boot.command.product.UpdateProductStockCommand;
import com.project.spring.boot.streaming.model.message.PurchaseCreatedMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final CommandExecutor commandExecutor;

    @KafkaListener(topics = "purchase", groupId = "main_service")
    public void listen(PurchaseCreatedMessage message) {
        commandExecutor.execute(UpdateProductStockCommand.class,
                                            UpdateProductStockCommandRequest.builder()
                                                                            .productId(message.getProduct().getId())
                                                                            .totalPurchase(message.getTotalPurchase())
                                                                            .build()).subscribe();
    }

}
