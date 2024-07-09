package com.nongsa.common.handler;

import com.nongsa.common.dto.ResponseDto;
import com.nongsa.common.handler.exception.CustomException;
import com.nongsa.common.handler.exception.CustomApiValidationException;
import com.nongsa.common.util.Script;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomApiValidationException.class)
    public ResponseEntity<?> validationApiException(CustomApiValidationException e) {
        return new ResponseEntity<>(new ResponseDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> apiException(CustomException e) {
        return new ResponseEntity<>(new ResponseDto<>(-1, e.getMessage(),null),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CustomException.class)
    public String exception(Exception e) {
        return Script.back(e.getMessage());
    }

}
