package com.herokuapp.crosses.advice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionApi {
    private String message;

    public ExceptionApi(String message) {
        this.message = message;
    }
}