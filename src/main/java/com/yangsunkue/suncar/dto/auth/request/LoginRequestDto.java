package com.yangsunkue.suncar.dto.auth.request;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class LoginRequestDto {

    private String userId;
    private String password;
}
