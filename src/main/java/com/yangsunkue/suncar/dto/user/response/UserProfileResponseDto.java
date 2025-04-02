package com.yangsunkue.suncar.dto.user.response;

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
}
