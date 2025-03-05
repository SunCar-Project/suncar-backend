package com.yangsunkue.suncar.exception.handler;

import com.yangsunkue.suncar.exception.BaseException;
import com.yangsunkue.suncar.exception.InvalidPasswordException;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.exception.UnauthorizedException;
import com.yangsunkue.suncar.exception.dto.ErrorResponseDto;
import com.yangsunkue.suncar.service.log.ErrorLogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 전역 예외 처리 핸들러 입니다.
 *
 * 특정 예외에 대하여 다음과 같은 동작을 수행합니다 :
 * -> 로깅
 * -> 상태코드 및 에러 응답 반환
 */
@RestControllerAdvice // @ControllerAdvice + @ResponseBody -> 응답을 자동으로 JSON 변환
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final ErrorLogService errorLogService;

    /**
     * 모든 커스텀 예외를 처리합니다.
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponseDto> handleBaseException(
            BaseException e,
            HttpServletRequest request
    ) {
        // 에러 로깅
        errorLogService.logError(e, request);

        // 에러 응답 생성
        ErrorResponseDto response = ErrorResponseDto.of(
                e.getMessage(),
                e.getStatus(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(e.getStatus())
                .body(response);
    }

    /**
     * Spring Security 관련 예외들을 처리합니다.
     */
    @ExceptionHandler({
            InternalAuthenticationServiceException.class,
            BadCredentialsException.class,
            AccessDeniedException.class,
            InsufficientAuthenticationException.class
    })
    public ResponseEntity<ErrorResponseDto> handleSecurityExceptions(
            Exception e,
            HttpServletRequest request
    ) {
        BaseException baseException;

        // InternalAuthenticationServiceException 처리
        if (e instanceof InternalAuthenticationServiceException) {
            Throwable cause = e.getCause();
            if (cause instanceof NotFoundException) {
                // 내부에 NotFoundException이 있으면 그것을 사용
                baseException = (NotFoundException) cause;
            } else {
                // 다른 내부 예외인 경우
                baseException = new UnauthorizedException("인증 처리 중 오류가 발생했습니다.");
            }
        }
        // BadCredentialsException 처리 (잘못된 비밀번호)
        else if (e instanceof BadCredentialsException) {
            baseException = new InvalidPasswordException();
        }
        // AccessDeniedException 처리 (권한 부족)
        else if (e instanceof AccessDeniedException) {
            baseException = new com.yangsunkue.suncar.exception.AccessDeniedException("접근 권한이 없습니다.");
        }
        // 그 외 인증 관련 예외
        else {
            baseException = new UnauthorizedException("인증이 필요합니다.");
        }

        // 에러 로깅
        errorLogService.logError(baseException, request);

        // 에러 응답 생성
        ErrorResponseDto response = ErrorResponseDto.of(
                baseException.getMessage(),
                baseException.getStatus(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(baseException.getStatus())
                .body(response);
    }




    /**
     * 500 InternalServerError - 예상치 못한 모든 예외를 처리합니다.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleUnexpectedException(
            Exception e,
            HttpServletRequest request
    ) {

        // 에러 로그 출력
        errorLogService.logError(e, request);

        // 에러 응답 생성
        ErrorResponseDto response = ErrorResponseDto.of(
                "Internal server error",
                HttpStatus.INTERNAL_SERVER_ERROR,
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
