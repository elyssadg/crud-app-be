package com.project.spring.boot.command;

import reactor.core.publisher.Mono;

public interface Command<R, T> {
    Mono<T> execute(R commandRequest);

    default Mono<T> fallback(Throwable throwable, R commandRequest) {
        return Mono.error(throwable);
    }

    default boolean validate(R commandRequest) {
        return true;
    }
}
