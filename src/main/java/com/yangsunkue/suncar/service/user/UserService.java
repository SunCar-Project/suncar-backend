package com.yangsunkue.suncar.service.user;

import com.yangsunkue.suncar.dto.user.UserProfileResponseDto;
import com.yangsunkue.suncar.dto.user.UserProfileUpdateRequestDto;
import com.yangsunkue.suncar.security.CustomUserDetails;

public interface UserService {

    /**
     * 현재 사용자 정보를 리턴합니다.
     */
    public UserProfileResponseDto getCurrentUserProfile(CustomUserDetails userDetails);

    /**
     * 현재 사용자 정보를 수정합니다.
     */
    public UserProfileResponseDto updateCurrentUserProfile(
            CustomUserDetails userDetails,
            UserProfileUpdateRequestDto dto
    );
}
