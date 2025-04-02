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
    public static final String DUMMY_DATA_NOT_SUPPORTED = "프로덕션 환경에서는 더미 데이터 생성이 지원되지 않습니다.";
}