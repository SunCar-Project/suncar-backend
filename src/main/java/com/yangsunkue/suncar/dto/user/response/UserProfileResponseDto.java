package com.yangsunkue.suncar.dto.user.response;

import com.yangsunkue.suncar.common.enums.UserRole;
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
