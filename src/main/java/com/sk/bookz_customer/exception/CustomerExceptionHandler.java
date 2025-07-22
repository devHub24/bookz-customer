package com.sk.bookz_customer.exception;

import com.sk.bookz_customer.controller.CustomerController;
import com.sk.bookz_customer.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
       String message = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage
               ).collect(Collectors.joining(", "));
       LOGGER.error(message);
       return ResponseEntity.badRequest().body(
               ErrorDto.builder()
                       .message(message)
                       .path(request.getDescription(false))
                       .timestamp(LocalDateTime.now())
                       .code(HttpStatus.BAD_REQUEST.value())
               .build()
       );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        LOGGER.error(ex.getMessage());
        return ResponseEntity.badRequest().body(ErrorDto.builder()
                .message(ex.getMessage())
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.BAD_REQUEST.value())
                .build());
    }

    @ExceptionHandler({CustomerAlreadyExistsException.class, CustomerNotFoundException.class})
    public ResponseEntity<ErrorDto> handleDataIntegrityViolationException(RuntimeException ex, WebRequest request) {
        LOGGER.error(ex.getMessage());
            return ResponseEntity.badRequest().body(
                    ErrorDto.builder()
                            .message(ex.getMessage())
                            .path(request.getDescription(false))
                            .timestamp(LocalDateTime.now())
                            .code(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
    }


}
