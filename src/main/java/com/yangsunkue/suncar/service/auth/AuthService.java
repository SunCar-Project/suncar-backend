package com.yangsunkue.suncar.service.auth;

import com.yangsunkue.suncar.dto.auth.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.SignUpResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.DuplicateResourceException;
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
     * 회원가입을 진행합니다.
     */
    @Transactional
    public SignUpResponseDto createUser(SignUpRequestDto dto) {

        // 중복 검사
        if (userRepository.existsByUserId(dto.getUserId())) {
            throw new DuplicateResourceException("User", dto.getUserId());
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("User", dto.getEmail());
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
}