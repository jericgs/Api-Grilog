package com.grilog.grilogapi.domain.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BusinessException(final String message) {
        super(message);
    }
}
