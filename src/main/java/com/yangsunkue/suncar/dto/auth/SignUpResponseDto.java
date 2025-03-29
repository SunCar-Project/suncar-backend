package com.yangsunkue.suncar.dto.auth;

import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.common.enums.UserRole;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Builder
public class SignUpResponseDto {

    private Long id;
    private String userId;
    private String email;
    private String username;
    private String phoneNumber;
    private UserRole role;

    public static SignUpResponseDto fromUser(User user) {
        return SignUpResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .email(user.getEmail())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }
}
