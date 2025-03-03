package com.yangsunkue.suncar.exception;


import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    /**
     * 404 Not Found
     *
     * @param resourceName 조회에 실패한 데이터 이름
     * @param fieldValue 조회 기준이 된 컬럼
     */
    public NotFoundException(String resourceName, Object fieldValue) {
        super(
                String.format("%s not found with field value: %s", resourceName, fieldValue),
                HttpStatus.NOT_FOUND
        );
    }
}
