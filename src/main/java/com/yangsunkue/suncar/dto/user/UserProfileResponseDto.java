package com.yangsunkue.suncar.dto.user;

import com.yangsunkue.suncar.common.enums.UserRole;
import com.yangsunkue.suncar.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class UserProfileResponseDto {

    private String userId;
    private String email;
    private String userName;
    private String phoneNumber;
    private UserRole role;

    public static UserProfileResponseDto toDto(CustomUserDetails userDetails) {
        return new UserProfileResponseDto(
                userDetails.getUserId(),
                userDetails.getEmail(),
                userDetails.getUsername(),
                userDetails.getPhoneNumber(),
                userDetails.getRole()
        );
    }
}
