package com.project.spring.boot.command.impl.user;

import com.project.spring.boot.command.model.request.user.LogoutUserCommandRequest;
import com.project.spring.boot.command.user.LogoutUserCommand;
import com.project.spring.boot.exception.DataNotFoundException;
import com.project.spring.boot.repository.UserRepository;
import com.project.spring.boot.web.model.response.user.UserWebResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class LogoutUserCommandImpl implements LogoutUserCommand {

    private final UserRepository userRepository;

    @Override
    public Mono<UserWebResponse> execute(LogoutUserCommandRequest commandRequest) {
        return userRepository.findFirstByToken(commandRequest.getToken())
                            .switchIfEmpty(Mono.error(new DataNotFoundException("No user with token " + commandRequest.getToken() + " found")))
                            .flatMap(user -> {
                                user.setToken(null);
                                user.setTokenExpiredAt(null);
                                return userRepository.save(user);
                            })
                            .map(user -> UserWebResponse.builder().user(user).build())
                            .doOnSuccess(response -> log.info("Successfully logout"))
                            .doOnError(response -> log.error("No user with token {} found", commandRequest.getToken()));
    }

}
