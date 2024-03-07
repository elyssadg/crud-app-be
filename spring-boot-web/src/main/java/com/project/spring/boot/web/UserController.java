package com.project.spring.boot.web;

import com.project.spring.boot.command.impl.executor.CommandExecutor;
import com.project.spring.boot.command.model.request.user.GetUserByTokenCommandRequest;
import com.project.spring.boot.command.model.request.user.LoginUserCommandRequest;
import com.project.spring.boot.command.model.request.user.LogoutUserCommandRequest;
import com.project.spring.boot.command.model.request.user.RegisterUserCommandRequest;
import com.project.spring.boot.command.user.GetUserByTokenCommand;
import com.project.spring.boot.command.user.LoginUserCommand;
import com.project.spring.boot.command.user.LogoutUserCommand;
import com.project.spring.boot.command.user.RegisterUserCommand;
import com.project.spring.boot.entity.User;
import com.project.spring.boot.web.model.request.user.GetUserByTokenWebRequest;
import com.project.spring.boot.web.model.request.user.LoginUserWebRequest;
import com.project.spring.boot.web.model.request.user.LogoutUserWebRequest;
import com.project.spring.boot.web.model.request.user.RegisterUserWebRequest;
import com.project.spring.boot.web.model.response.base.BaseResponse;
import com.project.spring.boot.web.model.response.user.UserWebResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/backend/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final CommandExecutor commandExecutor;

    @PostMapping(value = "/register")
    public Mono<BaseResponse<UserWebResponse>> register(@Valid @RequestBody RegisterUserWebRequest request) {
        return commandExecutor.execute(RegisterUserCommand.class,
                                            RegisterUserCommandRequest.builder()
                                                    .username(request.getUsername())
                                                    .password(request.getPassword())
                                                    .name(request.getName()).build())
                                .map(BaseResponse::ok);
    }

    @PostMapping(value = "/login")
    public Mono<BaseResponse<UserWebResponse>> login(@Valid @RequestBody LoginUserWebRequest request) {
        return commandExecutor.execute(LoginUserCommand.class,
                                            LoginUserCommandRequest.builder()
                                                    .username(request.getUsername())
                                                    .password(request.getPassword())
                                                    .build())
                                .map(BaseResponse::ok);
    }

    @GetMapping("/{token}")
    @Cacheable(cacheNames = "user", key = "#request.token")
    public Mono<BaseResponse<UserWebResponse>> getByToken(@PathVariable(value = "token", required = true) GetUserByTokenWebRequest request) {
        return commandExecutor.execute(GetUserByTokenCommand.class,
                                            GetUserByTokenCommandRequest.builder()
                                                    .token(request.getToken())
                                                    .build())
                                .map(BaseResponse::ok);
    }

    @PostMapping("/logout")
    @CacheEvict(cacheNames = "user", key = "#request.token")
    public Mono<BaseResponse<UserWebResponse>> logout(@RequestBody LogoutUserWebRequest request) {
        return commandExecutor.execute(LogoutUserCommand.class,
                                            LogoutUserCommandRequest.builder()
                                                                    .token(request.getToken())
                                                                    .build())
                                .map(BaseResponse::ok);
    }

}
