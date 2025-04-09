package com.yangsunkue.suncar.dto.auth.response;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class LoginResponseDto {

    private String userId;
    private String username;
    private String accessToken;
}
