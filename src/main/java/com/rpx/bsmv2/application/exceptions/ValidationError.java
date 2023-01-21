package com.rpx.bsmv2.application.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ValidationError extends StandardError {

    private final List<FieldMessage> fieldErrors = new ArrayList<>();

    public ValidationError(Instant timestamp, Integer status, String error, String message, String path) {
        super( timestamp,  status,  error,  message,  path);
    }

    public void addError(String fieldName, String fieldMessage) {
        fieldErrors.add(new FieldMessage(fieldName, fieldMessage));
    }


}
