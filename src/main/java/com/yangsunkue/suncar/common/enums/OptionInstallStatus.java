package com.yangsunkue.suncar.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 차량 안전장치 및 옵션 설치여부를 정의한 enum 입니다.
 */
@AllArgsConstructor
@Getter
public enum OptionInstallStatus {

    INSTALLED("장착"),
    OPTIONAL("선택옵션"),
    NOT_INSTALLED("미장착"),
    UNAVAILABLE("장착불가");

    private final String value;
}
