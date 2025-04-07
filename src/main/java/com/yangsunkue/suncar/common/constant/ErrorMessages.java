package com.yangsunkue.suncar.common.constant;

public class ErrorMessages {

    /**
     * Auth 관련 예외 메시지
     */
    public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String INVALID_PASSWORD = "비밀번호가 일치하지 않습니다.";
    public static final String DUPLICATE_USER_ID = "이미 사용 중인 아이디입니다";
    public static final String DUPLICATE_EMAIL = "이미 등록된 이메일 주소입니다";

    /**
     * Car 관련 예외 메시지
     */
    public static final String DUPLICATE_MAIN_IMAGE = "이미 메인 이미지가 존재합니다.";
    public static final String IMAGE_NOT_PRIMARY = "메인 이미지 생성 요청에는 isPrimary가 true 여야 합니다.";
    public static final String PRIMARY_IMAGES_NOT_ALLOWED = "일반 이미지 생성 요청에는 isPrimary가 false 여야 합니다.";
    public static final String CAR_LISTING_NOT_FOUND = "판매 등록된 차량 정보를 찾을 수 없습니다.";
}