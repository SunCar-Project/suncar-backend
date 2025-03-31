package com.yangsunkue.suncar.service.user;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.user.response.UserProfileResponseDto;
import com.yangsunkue.suncar.dto.user.request.UserProfileUpdateRequestDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.repository.user.UserRepository;
import com.yangsunkue.suncar.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    /**
     * 현재 사용자 정보를 수정합니다.
     */
    @Override
    @Transactional
    public UserProfileResponseDto updateCurrentUserProfile(
            CustomUserDetails userDetails,
            UserProfileUpdateRequestDto dto
    ) {
        User currentUser = userRepository.findByUserId(userDetails.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND));

        // 사용자 정보 수정
        currentUser.patch(dto);

        // dto 변환 및 리턴
        UserProfileResponseDto responseDto = UserProfileResponseDto.fromUser(currentUser);
        return responseDto;
    }
}
