package com.yangsunkue.suncar.common.constant;

/**
 * 클라이언트 응답용 메시지를 정의한 클래스 입니다.
 */
public class ResponseMessages {

    /**
     * Auth 관련 응답 메시지
     */
    public static final String USER_CREATED = "회원가입이 완료되었습니다.";
    public static final String LOGIN_SUCCESS = "로그인에 성공했습니다.";

    /**
     * User 관련 응답 메시지
     */
    public static final String USER_PROFILE_RETRIEVED = "회원 정보를 조회하였습니다.";
    public static final String USER_PROFILE_UPDATED =  "회원 정보를 수정하였습니다.";

    /**
     * Car 관련 응답 메시지
     */
    public static final String CAR_LIST_RETRIEVED = "차량 목록을 조회하였습니다.";
    public static final String MY_CAR_LIST_RETRIEVED = "내 차량 목록을 조회하였습니다.";
    public static final String CAR_DETAIL_RETRIEVED = "판매 차량 상세정보를 조회하였습니다.";
    public static final String CAR_REGISTERED = "차량을 판매등록하였습니다.";
    public static final String CAR_DETAIL_UPDATED = "판매 차량 상세정보를 수정하였습니다.";
    public static final String CAR_LISTING_DELETED = "판매 차량을 삭제하였습니다.";
}
