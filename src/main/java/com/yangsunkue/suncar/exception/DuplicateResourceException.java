package com.yangsunkue.suncar.exception;

import org.springframework.http.HttpStatus;

public class DuplicateResourceException extends BaseException{

    /**
     * 409 Conflict - 중복되는 데이터 존재
     */
    public DuplicateResourceException(String message) {
        super(
                message,
                HttpStatus.CONFLICT
        );
    }
}
