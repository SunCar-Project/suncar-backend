package com.yangsunkue.suncar.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 차량 모델명을 정의한 enum 입니다.
 */
@AllArgsConstructor
@Getter
public enum ModelName {
    E_CLASS("E클래스"),
    S_CLASS("S클래스"),
    SERIES_3("3시리즈"),
    SERIES_7("7시리즈"),
    A_6("A6"),
    RS_7("RS7"),
    SONATA("소나타"),
    STINGER("스팅어"),
    PORSCHE_911("911"),
    VANTAGE("밴티지");

    private final String value;
}
