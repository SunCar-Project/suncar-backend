package com.yangsunkue.suncar.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserProfileUpdateRequestDto {

    private String userName;
    private String phoneNumber;
}
