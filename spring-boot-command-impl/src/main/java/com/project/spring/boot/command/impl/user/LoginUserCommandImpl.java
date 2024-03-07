package com.project.spring.boot.command.impl.user;

import com.project.spring.boot.command.model.request.user.LoginUserCommandRequest;
import com.project.spring.boot.command.user.LoginUserCommand;
import com.project.spring.boot.entity.User;
import com.project.spring.boot.exception.BadRequestException;
import com.project.spring.boot.exception.DataNotFoundException;
import com.project.spring.boot.repository.UserRepository;
import com.project.spring.boot.web.model.response.user.UserWebResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginUserCommandImpl implements LoginUserCommand {

    private final UserRepository userRepository;

    @Override
    public Mono<UserWebResponse> execute(LoginUserCommandRequest commandRequest) {
        return userRepository.findByUsername(commandRequest.getUsername())
                .filter(user -> user.getPassword().equals(commandRequest.getPassword()))
                .flatMap(user -> {
                    user.setToken(UUID.randomUUID().toString());
                    user.setTokenExpiredAt(System.currentTimeMillis() + (7 * 24 * 3600 * 1000));
                    return userRepository.save(user);
                })
                .map(user -> UserWebResponse.builder()
                                            .user(user)
                                            .build())
                .switchIfEmpty(Mono.error(new BadRequestException("Invalid credentials")))
                .doOnSuccess(response -> log.info("User {} successfully logged in", response.getUser().getName()))
                .doOnError(response -> log.error("Failed login request"));
    }

}
