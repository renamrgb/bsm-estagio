package com.rpx.bsmv2.application.exceptions.business;

public class BusinessDatabaseViolationException extends RuntimeException {
    public BusinessDatabaseViolationException(String message) {
        super(message);
    }
}
