package com.yangsunkue.suncar.controller.user;

import com.yangsunkue.suncar.common.constant.ResponseMessages;
import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.dto.user.UserProfileResponseDto;
import com.yangsunkue.suncar.dto.user.UserProfileUpdateRequestDto;
import com.yangsunkue.suncar.security.CustomUserDetails;
import com.yangsunkue.suncar.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 관련 컨트롤러 입니다.
 */
@RestController
@RequestMapping("/users")
@ResponseStatus(HttpStatus.OK)
@Tag(name = "User")
@SecurityRequirement(name = "bearer-jwt")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 현재 사용자 정보를 조회합니다.
     */
    @GetMapping("/me")
    @Operation(summary = "현재 사용자 정보 조회")
    public ResponseDto<UserProfileResponseDto> getCurrentUserProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        UserProfileResponseDto userProfile = UserProfileResponseDto.fromUserDetails(userDetails);
        return ResponseDto.of(ResponseMessages.USER_PROFILE_RETRIEVED, userProfile);
    }

    /**
     * 현재 사용자 정보를 수정합니다.
     */
    @PatchMapping("/me")
    @Operation(summary = "현재 사용자 정보 수정")
    public ResponseDto<UserProfileResponseDto> updateCurrentUserProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody UserProfileUpdateRequestDto dto
    ) {
        UserProfileResponseDto updatedProfile = userService.updateCurrentUserProfile(userDetails, dto);
        return ResponseDto.of(ResponseMessages.USER_PROFILE_UPDATED, updatedProfile);
    }
}

















