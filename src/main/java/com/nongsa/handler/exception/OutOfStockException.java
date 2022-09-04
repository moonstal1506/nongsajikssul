package com.nongsa.handler.exception;

public class OutOfStockException extends RuntimeException{

    public OutOfStockException(String message) {
        super(message);
    }

}
