package com.nongsa.handler.exception;

import java.util.Map;

public class CustomApiValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Map<String, String> errorMap;

    public CustomApiValidationException(String message, Map<String, String> errorMap) {
        super(message);
        this.errorMap = errorMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

}
