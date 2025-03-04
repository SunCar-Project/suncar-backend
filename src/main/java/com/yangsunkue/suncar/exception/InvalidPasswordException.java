package com.yangsunkue.suncar.exception;

import com.yangsunkue.suncar.constant.ErrorMessages;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends BaseException{

    /**
     * 401 Unauthorized - 패스워드 불일치
     */
    public InvalidPasswordException() {
        super(
                ErrorMessages.INVALID_PASSWORD,
                HttpStatus.UNAUTHORIZED
        );
    }
}
