package com.yangsunkue.suncar.service.user;

import com.yangsunkue.suncar.dto.user.UserProfileResponseDto;
import com.yangsunkue.suncar.security.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    /**
     *
     * 현재 사용자 정보를 리턴합니다.
     */
    public UserProfileResponseDto getCurrentUserProfile() {

        // 현재 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        // userDetails로 dto제작하기
        UserProfileResponseDto responseDto = UserProfileResponseDto.toDto(userDetails);

        return responseDto;
    }
}
