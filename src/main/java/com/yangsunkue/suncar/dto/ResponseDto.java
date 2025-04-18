package com.yangsunkue.suncar.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * API 응답 형태의 일관성을 위한 Dto 입니다.
 *
 * @param <T> 모든 타입의 데이터를 포함할 수 있습니다.
 */
@AllArgsConstructor
@Getter
@Builder
public class ResponseDto<T> {

    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    // 데이터가 없는 경우 -> 메시지만 리턴
    public static <T> ResponseDto<T> of(String message) {
        return ResponseDto.<T>builder()
                .message(message)
                .build();
    }

    // 데이터가 있는 경우 -> 메시지 + 데이터 리턴
    public static <T> ResponseDto<T> of(String message, T data) {
        return ResponseDto.<T>builder()
                .message(message)
                .data(data)
                .build();
    }
}