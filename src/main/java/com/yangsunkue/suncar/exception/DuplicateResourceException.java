package com.yangsunkue.suncar.exception;

import org.springframework.http.HttpStatus;

public class DuplicateResourceException extends BaseException{

    /**
     * 409 Conflict
     */
    public DuplicateResourceException(String message) {
        super(
                message,
                HttpStatus.CONFLICT
        );
    }
}
