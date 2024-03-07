package com.project.spring.boot.web.model.response.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseResponse<T> implements Serializable {

    private int code;
    private String status;
    private T data;

    public static <R> BaseResponse<R> ok(R data) {
        return BaseResponse.<R>builder()
                .data(data)
                .status(HttpStatus.OK.name())
                .code(HttpStatus.OK.value())
                .build();
    }

}
