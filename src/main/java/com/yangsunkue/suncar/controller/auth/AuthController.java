package com.yangsunkue.suncar.controller;

import com.yangsunkue.suncar.dto.ResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * 인증 관련 컨트롤러 입니다.
 */
@RestController
@RequestMapping("/auth")
@ResponseStatus(HttpStatus.OK)
@RequiredArgsConstructor
public class AuthController {

//    private final AuthService authService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("signup")
    @Transactional
    public ResponseEntity<ResponseDto<User>> signUp() {

        
    }
}
