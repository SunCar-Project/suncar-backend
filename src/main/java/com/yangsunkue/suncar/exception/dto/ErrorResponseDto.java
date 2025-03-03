package com.yangsunkue.suncar.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * 에러 메시지 응답용 dto 입니다.
 */
@Getter
@AllArgsConstructor
public class ErrorResponseDto {

    private final String message;
    private final int status;
    private final String code;
    private final LocalDateTime timestamp;
    private final String path;

    public static ErrorResponseDto of(String message, HttpStatus status, String path) {
        return new ErrorResponseDto(
                message,
                status.value(),
                status.name(),
                LocalDateTime.now(),
                path
        );
    }
}