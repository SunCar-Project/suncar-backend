package com.yangsunkue.suncar.service.log;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 에러 로그를 출력하는 서비스 입니다.
 */
@Service
@Slf4j
public class ErrorLogService {
    public void logError(Exception e, HttpServletRequest request) {
        log.error("Exception occurred: {} at URI: {}",
                e.getMessage(),
                request.getRequestURI(),
                e // 예외 객체 자체를 전달하여 스택트레이스를 로그에 포함
        );

        /**
         * TODO
         * 추가적인 로깅 로직 작성
         * ex) DB에 저장, 모니터링 시스템에 전송 등
         */
    }
}
