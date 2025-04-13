package com.yangsunkue.suncar.dto.auth.request;

import com.yangsunkue.suncar.common.enums.UserRole;
import lombok.*;

/**
 * 회원가입 요청 데이터를 전달받는 dto 입니다.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
public class SignUpRequestDto {

    private String userId;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private UserRole role;
}
