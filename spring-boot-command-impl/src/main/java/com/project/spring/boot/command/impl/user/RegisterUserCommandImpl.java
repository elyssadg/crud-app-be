package com.project.spring.boot.command.impl.user;

import com.project.spring.boot.command.model.request.user.RegisterUserCommandRequest;
import com.project.spring.boot.command.user.RegisterUserCommand;
import com.project.spring.boot.entity.User;
import com.project.spring.boot.repository.UserRepository;
import com.project.spring.boot.web.model.response.user.UserWebResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegisterUserCommandImpl implements RegisterUserCommand {

    private final UserRepository userRepository;

    @Override
    public Mono<UserWebResponse> execute(RegisterUserCommandRequest commandRequest) {
        return Mono.fromCallable(() ->
                                    User.builder()
                                            .id(UUID.randomUUID().toString())
                                            .username(commandRequest.getUsername())
                                            .password(commandRequest.getPassword())
                                            .name(commandRequest.getName())
                                            .build()
                                ).flatMap(user -> userRepository.save(user.setAsNew()))
                                .map(user -> UserWebResponse.builder().user(user).build())
                                .doOnSuccess(response -> log.info("Successfully register new user with name {}", response.getUser().getName()));
    }

}
