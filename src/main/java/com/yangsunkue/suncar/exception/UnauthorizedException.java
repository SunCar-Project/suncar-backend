package com.yangsunkue.suncar.exception;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException{

    /**
     * 401 Unauthorized
     */
    public UnauthorizedException(String message) {
        super(
                message,
                HttpStatus.UNAUTHORIZED
        );
    }
}
