package com.project.spring.boot.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class DataNotFoundException extends Exception {
    public DataNotFoundException(String message) { super(message); }
}
