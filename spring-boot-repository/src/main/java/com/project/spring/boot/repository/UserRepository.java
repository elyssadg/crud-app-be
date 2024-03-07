package com.project.spring.boot.repository;

import com.project.spring.boot.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, String> {
    public Mono<User> findByUsername(String username);
    public Mono<User> findFirstByToken(String token);
}
