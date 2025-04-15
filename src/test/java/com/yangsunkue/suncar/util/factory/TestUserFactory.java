package com.yangsunkue.suncar.util.factory;

import com.yangsunkue.suncar.common.enums.UserRole;
import com.yangsunkue.suncar.entity.user.User;

public class TestUserFactory {

    private static final String PLAIN_PASSWORD = "1234";
    private static final String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5c2s5NTI2IiwiaWF0IjoxNzQ0MTY4NTUzLCJleHAiOjE3NDQyNTQ5NTN9.Hc43KWQbolIXyD3sUnk6b683SsDfniYn8zWZygNWp60";

    public static User createUser() {
        return User.builder()
                .id(1L)
                .userId("testUser")
                .email("testUser@test.com")
                .username("테스트사용자")
                .passwordHash("$2a$10$.YFMGmqbuDQ3hpn8f0mcP.gDufd2liVOlxo.NNWArb9g7qW5Zef0i")
                .phoneNumber("01012345678")
                .role(UserRole.회원)
                .build();
    }

    /**
     * DB에 save 되기 전의 User 객체를 생성합니다.
     *
     * @return - id 필드 값이 null인 User 객체
     */
    public static User createUnSavedUser() {
        User user = createUser();

        return user.toBuilder()
                .id(null)
                .build();
    }

    public static String getPlainPassword() {
        return PLAIN_PASSWORD;
    }

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }
}
