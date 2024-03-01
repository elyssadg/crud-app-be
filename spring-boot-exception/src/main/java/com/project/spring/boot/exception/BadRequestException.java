package com.project.spring.boot.exception;

import lombok.Data;

@Data
public class BadRequestException extends Exception {
    public BadRequestException(String message) { super(message); }
}
