package com.yangsunkue.suncar.service.auth;

import com.yangsunkue.suncar.dto.auth.LoginRequestDto;
import com.yangsunkue.suncar.dto.auth.LoginResponseDto;
import com.yangsunkue.suncar.dto.auth.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.SignUpResponseDto;
import com.yangsunkue.suncar.entity.user.User;

public interface AuthService {

    /**
     * 일반 회원가입을 진행합니다.
     */
    User createUser(SignUpRequestDto dto);

    /**
     * 일반 로그인을 진행합니다.
     */
    LoginResponseDto login(LoginRequestDto dto);
}
