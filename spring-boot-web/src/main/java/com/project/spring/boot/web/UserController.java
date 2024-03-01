package com.project.spring.boot.web;

import com.project.spring.boot.command.impl.executor.CommandExecutor;
import com.project.spring.boot.command.model.request.RegisterUserCommandRequest;
import com.project.spring.boot.command.user.RegisterUserCommand;
import com.project.spring.boot.web.model.request.LoginUserWebRequest;
import com.project.spring.boot.web.model.request.RegisterUserWebRequest;
import com.project.spring.boot.web.model.response.UserWebResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CommandExecutor commandExecutor;

    @PostMapping(value = "/register")
    public Mono<UserWebResponse> register(@Valid @RequestBody RegisterUserWebRequest request) {
        return commandExecutor.execute(RegisterUserCommand.class,
                        RegisterUserCommandRequest.builder()
                                .username(request.getUsername())
                                .password(request.getPassword())
                                .name(request.getName()).build());
    }

//    @PostMapping(value = "/login")
//    public UserWebResponse login(@Valid @RequestBody LoginUserWebRequest request) {
//        log.info("Login: user with username {}", request.getUsername());
//        return userService.login(request.getUsername(),
//                request.getPassword());
//    }

}
