package com.project.spring.boot.web.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserWebRequest {
    @NotBlank(message = "Username must not be empty")
    private String username;

    @NotBlank(message = "Password must not be empty")
    private String password;

    @NotBlank(message = "Name must not be empty")
    private String name;
}
