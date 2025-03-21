package com.yangsunkue.suncar.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserProfileUpdateRequestDto {

    private String userName;
    private String phoneNumber;
}
