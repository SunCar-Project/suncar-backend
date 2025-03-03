package com.yangsunkue.suncar.exception;

import org.springframework.http.HttpStatus;

public class DuplicateResourceException extends BaseException{

    /**
     * 409 Conflict
     *
     * @param resourceName 중복된 데이터 이름
     * @param fieldValue 중복된 필드 값
     */
    public DuplicateResourceException(String resourceName, Object fieldValue) {
        super(
                String.format("%s already exists with field value: %s", resourceName, fieldValue),
                HttpStatus.CONFLICT
        );
    }
}
