package com.yangsunkue.suncar.service.auth;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.auth.request.LoginRequestDto;
import com.yangsunkue.suncar.dto.auth.response.LoginResponseDto;
import com.yangsunkue.suncar.dto.auth.request.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.response.SignUpResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.DuplicateResourceException;
import com.yangsunkue.suncar.mapper.UserMapper;
import com.yangsunkue.suncar.repository.user.UserRepository;
import com.yangsunkue.suncar.security.CustomUserDetails;
import com.yangsunkue.suncar.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    /**
     * 일반 회원가입을 진행합니다.
     */
    @Override
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
        User user = userMapper.fromSignUpRequestDto(dto, hashedPassword);

        // DB 에 저장 및 DTO로 변환
        User saved = userRepository.save(user);
        SignUpResponseDto userDto = userMapper.toSignUpResponseDto(saved);

        // 리턴
        return userDto;
    }

    /**
     * 일반 로그인을 진행합니다.
     */
    @Override
    public LoginResponseDto login(LoginRequestDto dto) {

        // Spring Security의 AuthenticationManager를 사용해 인증
        // 인증 실패 시 BadCredentialsException 발생 -> 전역 핸들러 처리 구현
        // 인증 성공 시 내부적으로 loadUserByUsername()을 호출하여 Authentication 객체 안에 UserDetails를 넣는다
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUserId(), dto.getPassword())
        );

        // 인증된 사용자 정보 가져오기
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // JWT 토큰 생성
        String token = jwtUtil.generateToken(userDetails);

        // responseDto로 변환
        LoginResponseDto loginDto = userMapper.toLoginResponseDtoFromUserDetails(userDetails, token);

        // 리턴
        return loginDto;
    }
}