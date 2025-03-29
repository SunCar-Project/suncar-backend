package com.yangsunkue.suncar.controller.auth;

import com.yangsunkue.suncar.common.constant.ResponseMessages;
import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.dto.auth.LoginRequestDto;
import com.yangsunkue.suncar.dto.auth.LoginResponseDto;
import com.yangsunkue.suncar.dto.auth.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.SignUpResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth")
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
    @Operation(summary = "일반 회원가입")
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(@RequestBody SignUpRequestDto dto) {

        User created = authService.createUser(dto);
        SignUpResponseDto userDto = SignUpResponseDto.fromUser(created);
        ResponseDto<SignUpResponseDto> response = ResponseDto.of(ResponseMessages.USER_CREATED, userDto);

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
    @Operation(summary = "로그인")
    public ResponseDto<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        LoginResponseDto response = authService.login(dto);
        return ResponseDto.of(ResponseMessages.LOGIN_SUCCESS, response);
    }
}
