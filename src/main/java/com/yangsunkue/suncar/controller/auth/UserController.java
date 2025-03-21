package com.yangsunkue.suncar.controller.auth;

import com.yangsunkue.suncar.common.constant.ResponseMessages;
import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.dto.user.UserProfileResponseDto;
import com.yangsunkue.suncar.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 유저 관련 컨트롤러 입니다.
 */
@RestController
@RequestMapping("/users")
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 현재 사용자 정보를 리턴합니다.
     */
    @GetMapping("/me")
    public ResponseDto<UserProfileResponseDto> getCurrentUserProfile() {
        UserProfileResponseDto userProfile = userService.getCurrentUserProfile();
        return ResponseDto.of(ResponseMessages.USER_PROFILE_RETRIEVED, userProfile);
    }
}
