package com.yangsunkue.suncar.exception;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends BaseException {

    /**
     * 403 Forbidden
     */
    public AccessDeniedException(String message) {
        super(
                message,
                HttpStatus.FORBIDDEN
        );
    }
}
