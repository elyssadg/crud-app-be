package com.project.spring.boot.web;

import com.project.spring.boot.exception.BadRequestException;
import com.project.spring.boot.exception.DataNotFoundException;
import com.project.spring.boot.web.model.response.base.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@CrossOrigin(origins = "http://localhost:5173")
public class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<BaseErrorResponse> handleValidationExceptions(
            WebExchangeBindException ex) {
        Map<Integer, String> errors = new HashMap<>();
        List<ObjectError> errorList = ex.getBindingResult().getAllErrors();
        for (ObjectError e : errorList) {
            String errorMessage = e.getDefaultMessage();
            errors.put(errorList.indexOf(e), errorMessage);
        }
        return Mono.just(BaseErrorResponse.builder()
                                        .status(HttpStatus.BAD_REQUEST.name())
                                        .code(HttpStatus.BAD_REQUEST.value())
                                        .message(errors.get(0))
                                        .build());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public Mono<BaseErrorResponse> handleBadRequestExceptions(
            BadRequestException ex) {
        return Mono.just(BaseErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DataNotFoundException.class)
    public Mono<BaseErrorResponse> handleDataNotFoundExceptions(
            DataNotFoundException ex) {
        return Mono.just(BaseErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.name())
                .code(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build());
    }

}
