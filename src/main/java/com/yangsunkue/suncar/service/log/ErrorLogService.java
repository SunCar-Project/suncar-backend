package com.yangsunkue.suncar.service.log;

import jakarta.servlet.http.HttpServletRequest;

public interface ErrorLogService {

    /**
     * 에러 로그를 출력합니다.
     */
    void logError(Exception e, HttpServletRequest request);
}
