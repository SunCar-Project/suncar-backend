package com.yangsunkue.suncar.dto.auth.response;

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
}
