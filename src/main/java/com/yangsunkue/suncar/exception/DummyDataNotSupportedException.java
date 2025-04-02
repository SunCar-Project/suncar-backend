package com.yangsunkue.suncar.exception;

import org.springframework.http.HttpStatus;

public class DummyDataNotSupportedException extends BaseException {

    /**
     * 501 Not Implemented
     */
    public DummyDataNotSupportedException (String message) {
        super(
                message,
                HttpStatus.NOT_IMPLEMENTED
        );
    }
}
