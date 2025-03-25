package com.yangsunkue.suncar.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 차량 브랜드명을 정의한 enum 입니다.
 */
@AllArgsConstructor
@Getter
public enum BrandName {
    HYUNDAI("현대"),
    KIA("기아"),
    MERCEDES_BENZ("벤츠"),
    BMW("BMW"),
    AUDI("아우디"),
    PORSCHE("포르쉐"),
    ASTON_MARTIN("애스턴마틴");

    private final String value;
}
