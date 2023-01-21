package com.rpx.bsmv2.application.exceptions.business;

public class BusinessResourceNotFoundException extends RuntimeException {
    public BusinessResourceNotFoundException(String message) {
        super(message);
    }
}
