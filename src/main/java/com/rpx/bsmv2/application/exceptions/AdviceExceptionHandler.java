package com.rpx.bsmv2.application.exceptions;

import com.rpx.bsmv2.application.exceptions.business.BusinessResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

@ControllerAdvice
public class AdviceExceptionHandler {


    @ExceptionHandler(BusinessResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public StandardError resourceNotfound(BusinessResourceNotFoundException e, HttpServletRequest request) {
        return StandardError.builder()
                .withMessage(e.getMessage())
                .withPath(request.getRequestURI())
                .withTimestamp(Instant.now())
                .withError("Resource not found")
                .withStatus(HttpStatus.NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationError validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError validationError = new ValidationError(Instant.now(),
                HttpStatus.BAD_REQUEST.value(),"Validação error",
                "Validação de error", request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            validationError.addError(f.getField(), f.getDefaultMessage());
        }

        return validationError;
    }
}
