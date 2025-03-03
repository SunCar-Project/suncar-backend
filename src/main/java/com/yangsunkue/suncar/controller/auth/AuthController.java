package com.yangsunkue.suncar.controller.auth;

import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.dto.auth.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.SignUpResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
     * 회원가입을 진행합니다.
     */
    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<ResponseDto<SignUpResponseDto>> signUp(@RequestBody SignUpRequestDto dto) {

        SignUpResponseDto created = authService.createUser(dto);

        ResponseDto<SignUpResponseDto> response = ResponseDto.of("회원 등록 성공", created);

        /**
         * TODO
         * 단일 회원(User) 조회 엔드포인트 URI 리턴해주기
         */
        return ResponseEntity.created(URI.create("/auth/signup"))
                .body(response);
    }
}
