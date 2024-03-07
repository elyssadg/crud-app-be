package com.project.spring.boot.web.model.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters")
    private String username;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 8, message = "Password length must be more than 8 characters")
    private String password;

    @NotBlank(message = "Name must not be empty")
    @Size(min = 5, max = 30, message = "Username length must be between 5 and 30 characters")
    private String name;
}
