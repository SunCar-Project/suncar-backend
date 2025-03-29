package com.yangsunkue.suncar.exception;

import org.springframework.http.HttpStatus;

public class InvalidArgumentException extends BaseException {

    /**
     * 400 Bad Request
     */
    public InvalidArgumentException(String message) {
        super(
                message,
                HttpStatus.BAD_REQUEST
        );
    }
}
