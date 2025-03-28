package com.yangsunkue.suncar.dto.user;

import com.yangsunkue.suncar.common.enums.UserRole;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.security.CustomUserDetails;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class UserProfileResponseDto {

    private String userId;
    private String email;
    private String userName;
    private String phoneNumber;
    private UserRole role;

    public static UserProfileResponseDto fromUserDetails(CustomUserDetails userDetails) {
        return new UserProfileResponseDto(
                userDetails.getUserId(),
                userDetails.getEmail(),
                userDetails.getUsername(),
                userDetails.getPhoneNumber(),
                userDetails.getRole()
        );
    }

    public static UserProfileResponseDto fromUser(User user) {
        return new UserProfileResponseDto(
                user.getUserId(),
                user.getEmail(),
                user.getUsername(),
                user.getPhoneNumber(),
                user.getRole()
        );
    }
}
