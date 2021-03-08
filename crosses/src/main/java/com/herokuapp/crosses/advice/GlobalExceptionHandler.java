package com.herokuapp.crosses.advice;

import com.herokuapp.crosses.exception.GameNotFoundException;
import com.herokuapp.crosses.exception.UnrecognizedGamerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {GameNotFoundException.class, UnrecognizedGamerException.class})
    public ResponseEntity<ExceptionApi> handleRuntimeGameEx(RuntimeException ex) {
        ExceptionApi api = new ExceptionApi(ex.getMessage());
        return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ExceptionApi> handleRuntime(RuntimeException ex) {
        ExceptionApi api = new ExceptionApi(ex.getMessage());
        return new ResponseEntity<>(api, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<ExceptionApi> handleAuthenticate(AuthenticationCredentialsNotFoundException ex) {
        return new ResponseEntity<>(new ExceptionApi(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}