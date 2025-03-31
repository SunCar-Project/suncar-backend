package com.yangsunkue.suncar.dto.user.request;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserProfileUpdateRequestDto {

    private String userName;
    private String phoneNumber;
}
