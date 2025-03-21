package com.yangsunkue.suncar.service.user;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.user.UserProfileResponseDto;
import com.yangsunkue.suncar.dto.user.UserProfileUpdateRequestDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.repository.user.UserRepository;
import com.yangsunkue.suncar.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 현재 사용자 정보를 리턴합니다.
     */
    public UserProfileResponseDto getCurrentUserProfile() {

        // 현재 사용자 정보 가져오기
        CustomUserDetails userDetails = getCurrentUserDetails();

        // userDetails로 dto제작하기
        UserProfileResponseDto responseDto = UserProfileResponseDto.fromUserDetails(userDetails);

        return responseDto;
    }

    /**
     * 현재 사용자 정보를 수정합니다.
     */
    @Transactional
    public UserProfileResponseDto updateCurrentUserProfile(UserProfileUpdateRequestDto dto) {

        // 현재 사용자 정보 가져오기
        CustomUserDetails userDetails = getCurrentUserDetails();
        User currentUser = userRepository.findByUserId(userDetails.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND));

        // 사용자 정보 수정
        currentUser.patch(dto);

        // dto 변환 및 리턴
        UserProfileResponseDto responseDto = UserProfileResponseDto.fromUser(currentUser);
        return responseDto;
    }

    private CustomUserDetails getCurrentUserDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) auth.getPrincipal();
    }
}
