package com.project.spring.boot.streaming;

import com.project.spring.boot.streaming.model.message.PurchaseCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@Component
@RequiredArgsConstructor
public class Producer {

    private final KafkaTemplate<String, PurchaseCreatedMessage> kafkaTemplate;

    @SneakyThrows
    public SendResult<String, PurchaseCreatedMessage> sendMessage(String topic, PurchaseCreatedMessage message) {
        return kafkaTemplate.send(topic, message).get();
    }

}
