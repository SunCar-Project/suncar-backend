package com.yangsunkue.suncar.service.auth;

import com.yangsunkue.suncar.dto.auth.request.LoginRequestDto;
import com.yangsunkue.suncar.dto.auth.response.LoginResponseDto;
import com.yangsunkue.suncar.dto.auth.request.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.response.SignUpResponseDto;

public interface AuthService {

    /**
     * 일반 회원가입을 진행합니다.
     */
    SignUpResponseDto createUser(SignUpRequestDto dto);

    /**
     * 일반 로그인을 진행합니다.
     */
    LoginResponseDto login(LoginRequestDto dto);
}
