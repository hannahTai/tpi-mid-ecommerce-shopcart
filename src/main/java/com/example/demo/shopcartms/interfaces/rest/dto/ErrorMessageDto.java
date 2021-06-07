package com.example.demo.shopcartms.interfaces.rest.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ErrorMessageDto {

    private String timestamp;

    private Integer status;

    private String error;

    private String message;

    List<FieldError> errors;

    private String path;

}
