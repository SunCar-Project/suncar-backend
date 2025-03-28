package com.yangsunkue.suncar.dto.auth;

import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.common.enums.UserRole;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class SignUpResponseDto {

    private Long id;
    private String userId;
    private String email;
    private String username;
    private String phoneNumber;
    private UserRole role;

    public static SignUpResponseDto toDto(User user) {
        return new SignUpResponseDto(
                user.getId(),
                user.getUserId(),
                user.getEmail(),
                user.getUsername(),
                user.getPhoneNumber(),
                user.getRole()
        );
    }
}
