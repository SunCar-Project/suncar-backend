package com.yangsunkue.suncar.util;

import java.util.Random;
import java.util.UUID;

/**
 * 랜덤한 숫자/문자열 생성 메서드를 제공하는 유틸리티 클래스 입니다.
 */
public class RandomUtils {

    /**
     * 원하는 길이의 랜덤 UUID를 생성합니다.
     * 입력값이 유효하지 않을 경우 32자 UUID를 생성합니다.
     *
     * @param length - 원하는 uuid 길이 (1 ~ 32자)
     * @return - 지정된 length 길이의 랜덤 uuid 문자열
     */
    public static String createUuid(Integer length) {

        int validLength = 32;
        if (length != null && length > 0 && length <= 32) {
            validLength = length;
        }

        // 랜덤 UUID 생성
        UUID uuid = UUID.randomUUID();
        String fullUuid = uuid.toString().replace("-", "");

        // UUID를 원하는 길이로 자르기
        String shortUuid = fullUuid.substring(0, validLength);
        return shortUuid;
    }

    /**
     * true 또는 false를 랜덤하게 생성합니다.
     *
     * @return - 랜덤 boolean 값
     */
    public static boolean randomBoolean() {
        return new Random().nextBoolean();
    }
}
