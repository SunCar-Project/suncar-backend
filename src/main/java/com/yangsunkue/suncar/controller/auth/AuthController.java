package com.yangsunkue.suncar.controller.auth;

import com.yangsunkue.suncar.common.constant.ResponseMessages;
import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.dto.auth.LoginRequestDto;
import com.yangsunkue.suncar.dto.auth.LoginResponseDto;
import com.yangsunkue.suncar.dto.auth.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.SignUpResponseDto;
import com.yangsunkue.suncar.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * 인증 관련 컨트롤러 입니다.
 */
@RestController
@RequestMapping("/auth")
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    /**
     * 일반 회원가입을 진행합니다.
     */
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(@RequestBody SignUpRequestDto dto) {

        SignUpResponseDto created = authService.createUser(dto);

        ResponseDto<SignUpResponseDto> response = ResponseDto.of(ResponseMessages.USER_CREATED, created);

        /**
         * TODO
         * 단일 회원(User) 조회 엔드포인트 URI 리턴해주기
         */
        return ResponseEntity.created(URI.create("/auth/signup"))
                .body(response);
    }

    /**
     * 일반 로그인을 진행합니다.
     * JWT 토큰을 발급합니다.
     */
    @PostMapping("/login")
    public ResponseDto<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {

        LoginResponseDto response = authService.login(dto);

        return ResponseDto.of(ResponseMessages.LOGIN_SUCCESS, response);
    }
}
