package com.project.spring.boot.command.impl.user;

import com.project.spring.boot.command.model.request.RegisterUserCommandRequest;
import com.project.spring.boot.command.user.RegisterUserCommand;
import com.project.spring.boot.entity.User;
import com.project.spring.boot.repository.UserRepository;
import com.project.spring.boot.web.model.response.UserWebResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegisterUserCommandImpl implements RegisterUserCommand {

    private UserRepository userRepository;

    @Override
    public Mono<UserWebResponse> execute(RegisterUserCommandRequest commandRequest) {
        return Mono.just(User.builder()
                        .username(commandRequest.getUsername())
                        .password(commandRequest.getPassword())
                        .name(commandRequest.getName())
                        .build())
                .flatMap(userRepository::save)
                .flatMap(user -> Mono.just(UserWebResponse.builder().user(user).build()))
                .doOnSuccess(response -> log.info("Successfully register new user with name {}", response.getUser().getName()));
    }

}
