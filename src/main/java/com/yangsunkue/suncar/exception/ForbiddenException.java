package com.yangsunkue.suncar.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseException {

    /**
     * 403 Forbidden
     */
    public ForbiddenException(String message) {
        super(
                message,
                HttpStatus.FORBIDDEN
        );
    }
}
