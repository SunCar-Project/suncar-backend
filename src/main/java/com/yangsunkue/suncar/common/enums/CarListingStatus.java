package com.yangsunkue.suncar.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 차량 판매 등록 상태를 정의한 enum 입니다.
 */
@AllArgsConstructor
@Getter
public enum CarListingStatus {

    FOR_SALE("판매중"),
    RESERVED("예약중"),
    SOLD("판매완료");

    private final String value;
}