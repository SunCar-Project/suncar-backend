package com.yangsunkue.suncar.exception;


import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    /**
     * 404 Not Found
     */
    public NotFoundException(String message) {
        super(
                message,
                HttpStatus.NOT_FOUND
        );
    }
}
