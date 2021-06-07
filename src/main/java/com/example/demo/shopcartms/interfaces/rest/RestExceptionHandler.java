package com.example.demo.shopcartms.interfaces.rest;

import com.example.demo.shopcartms.interfaces.rest.dto.ErrorMessageDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxx");

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageDto handleException(MethodArgumentNotValidException ex, WebRequest request) {
        String timestamp = ZonedDateTime.now().format(dateTimeFormatter);
        Integer httpStatus = HttpStatus.BAD_REQUEST.value();
        String error =  HttpStatus.BAD_REQUEST.getReasonPhrase();
        String message = ex.getMessage();
        message = message.substring(0, message.indexOf(": "));
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        return ErrorMessageDto.builder()
                .timestamp(timestamp)
                .status(httpStatus)
                .error(error)
                .message(message)
                .errors(errors)
                .path(path)
                .build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageDto handleException(RuntimeException ex, WebRequest request) {
        log.error("RestExceptionHandler.RuntimeException: " + ex);
        String timestamp = ZonedDateTime.now().format(dateTimeFormatter);
        Integer httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error =  HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        String message = ex.getMessage();
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        return ErrorMessageDto.builder()
                .timestamp(timestamp)
                .status(httpStatus)
                .error(error)
                .message(message)
                .path(path)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageDto handleException(Exception ex, WebRequest request) {
        log.error("RestExceptionHandler.Exception: " + ex);
        String timestamp = ZonedDateTime.now().format(dateTimeFormatter);
        Integer httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        String error =  HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        String message = ex.getMessage();
        String path = ((ServletWebRequest) request).getRequest().getRequestURI();
        return ErrorMessageDto.builder()
                .timestamp(timestamp)
                .status(httpStatus)
                .error(error)
                .message(message)
                .path(path)
                .build();
    }

}
