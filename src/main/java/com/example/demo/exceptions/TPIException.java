package com.example.demo.exceptions;

public class TPIException extends RuntimeException {

    public TPIException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

}
