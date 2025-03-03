package com.yangsunkue.suncar.dto.auth;

import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
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
