package com.project.spring.boot.command.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserCommandRequest {
    private String username;

    private String password;

    private String name;
}
