package com.yangsunkue.suncar.service.auth;

import com.yangsunkue.suncar.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.auth.LoginRequestDto;
import com.yangsunkue.suncar.dto.auth.LoginResponseDto;
import com.yangsunkue.suncar.dto.auth.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.SignUpResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.DuplicateResourceException;
import com.yangsunkue.suncar.exception.InvalidPasswordException;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 일반 회원가입을 진행합니다.
     */
    @Transactional
    public SignUpResponseDto createUser(SignUpRequestDto dto) {

        // 중복 검사
        if (userRepository.existsByUserId(dto.getUserId())) {
            throw new DuplicateResourceException(ErrorMessages.DUPLICATE_USER_ID);
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException(ErrorMessages.DUPLICATE_EMAIL);
        }

        // 패스워드 해시 및 엔티티로 변환
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        User userEntity = SignUpRequestDto.toEntity(dto, hashedPassword);

        // DB 에 저장
        User saved = userRepository.save(userEntity);

        // ResponseDto로 변환
        SignUpResponseDto savedDto = SignUpResponseDto.toDto(saved);

        // 리턴
        return savedDto;
    }

    /**
     * 일반 로그인을 진행합니다.
     */
    public LoginResponseDto login(LoginRequestDto dto) {

        // 아이디 검증
        User user = userRepository.findByUserId(dto.getUserId());
        if (user == null) {
            throw new NotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        // 패스워드 검증
        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new InvalidPasswordException();
        }

        // TODO : JWT 토큰 생성

        // responseDto로 변환
        LoginResponseDto responseDto = LoginResponseDto.toDto(user);

        // 리턴
        return responseDto;
    }
}