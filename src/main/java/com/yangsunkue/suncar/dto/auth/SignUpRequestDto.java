package com.yangsunkue.suncar.dto.auth;

import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.common.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 회원가입 요청 데이터를 전달받는 dto 입니다.
 */
@AllArgsConstructor
@Getter
@ToString
public class SignUpRequestDto {

    private String userId;
    private String email;
    private String username;
    private String password;
    private String phoneNumber;
    private UserRole role;

    public static User toEntity(SignUpRequestDto dto, String hashedPassword) {
        return new User(
                null,
                dto.getUserId(),
                dto.getEmail(),
                dto.getUsername(),
                hashedPassword,
                dto.getPhoneNumber(),
                dto.getRole()
        );
    }
}
