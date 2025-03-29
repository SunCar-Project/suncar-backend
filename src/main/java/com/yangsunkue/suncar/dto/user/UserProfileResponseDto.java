package com.yangsunkue.suncar.dto.user;

import com.yangsunkue.suncar.common.enums.UserRole;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.security.CustomUserDetails;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class UserProfileResponseDto {

    private String userId;
    private String email;
    private String userName;
    private String phoneNumber;
    private UserRole role;

    public static UserProfileResponseDto fromUserDetails(CustomUserDetails userDetails) {
        return UserProfileResponseDto.builder()
                .userId(userDetails.getUserId())
                .email(userDetails.getEmail())
                .userName(userDetails.getUsername())
                .phoneNumber(userDetails.getPhoneNumber())
                .role(userDetails.getRole())
                .build();
    }

    public static UserProfileResponseDto fromUser(User user) {
        return UserProfileResponseDto.builder()
                .userId(user.getUserId())
                .email(user.getEmail())
                .userName(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }
}
