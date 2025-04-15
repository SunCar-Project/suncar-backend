package com.yangsunkue.suncar.service.auth;

import com.yangsunkue.suncar.dto.auth.request.LoginRequestDto;
import com.yangsunkue.suncar.dto.auth.request.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.response.LoginResponseDto;
import com.yangsunkue.suncar.dto.auth.response.SignUpResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.mapper.UserMapper;
import com.yangsunkue.suncar.repository.user.UserRepository;
import com.yangsunkue.suncar.security.CustomUserDetails;
import com.yangsunkue.suncar.security.JwtUtil;
import com.yangsunkue.suncar.util.factory.TestUserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authServiceImpl;

    /** 의존성 */
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UserMapper userMapper;

    /** 테스트 데이터 */
    private User testUser;
    private Authentication testAuthentication;
    private CustomUserDetails testUserDetails;
    private SignUpRequestDto testSignUpRequestDto;
    private SignUpResponseDto testSignUpResponseDto;
    private LoginRequestDto testLoginRequestDto;
    private LoginResponseDto testLoginResponseDto;

    /** 테스트 데이터 초기화 */
    @BeforeEach
    void setup() {

        testUser = TestUserFactory.createUser();
        testAuthentication = mock(Authentication.class);
        testUserDetails = mock(CustomUserDetails.class);

        testSignUpRequestDto = SignUpRequestDto.builder()
                .userId(testUser.getUserId())
                .email(testUser.getEmail())
                .username(testUser.getUsername())
                .password(TestUserFactory.getPlainPassword())
                .phoneNumber(testUser.getPhoneNumber())
                .role(testUser.getRole())
                .build();

        testSignUpResponseDto = SignUpResponseDto.builder()
                .id(testUser.getId())
                .userId(testUser.getUserId())
                .email(testUser.getEmail())
                .username(testUser.getUsername())
                .phoneNumber(testUser.getPhoneNumber())
                .role(testUser.getRole())
                .build();

        testLoginRequestDto = LoginRequestDto.builder()
                .userId(testUser.getUserId())
                .password(TestUserFactory.getPlainPassword())
                .build();

        testLoginResponseDto = LoginResponseDto.builder()
                .userId(testUser.getUserId())
                .username(testUser.getUsername())
                .accessToken(TestUserFactory.getAccessToken())
                .build();
    }

    @Test
    @DisplayName("일반 회원가입 테스트")
    void createUserTest() {

        // given
        when(userRepository.existsByUserId(any(String.class))).thenReturn(false);
        when(userRepository.existsByEmail(any(String.class))).thenReturn(false);

        when(passwordEncoder.encode(any(String.class))).thenReturn(testUser.getPasswordHash());
        when(userMapper.fromSignUpRequestDto(any(SignUpRequestDto.class), any(String.class))).thenReturn(TestUserFactory.createUnSavedUser());

        when(userRepository.save(any(User.class))).thenReturn(testUser);
        when(userMapper.toSignUpResponseDto(any(User.class))).thenReturn(testSignUpResponseDto);

        // when
        SignUpResponseDto result = authServiceImpl.createUser(testSignUpRequestDto);

        // then
        verify(userRepository).existsByUserId(testSignUpRequestDto.getUserId());
        verify(userRepository).existsByEmail(testSignUpRequestDto.getEmail());

        verify(passwordEncoder).encode(testSignUpRequestDto.getPassword());
        verify(userMapper).fromSignUpRequestDto(testSignUpRequestDto, testUser.getPasswordHash());

        User unSavedUser = TestUserFactory.createUnSavedUser();
        verify(userRepository).save(argThat(user ->
                user.getUserId().equals(unSavedUser.getUserId()) &&
                user.getEmail().equals(unSavedUser.getEmail())
        ));
        verify(userMapper).toSignUpResponseDto(testUser);

        assertThat(result.getId()).isEqualTo(testUser.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(testSignUpResponseDto);
    }

    @Test
    @DisplayName("일반 로그인 테스트")
    void loginTest() {

        // given
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(testAuthentication);
        when(testAuthentication.getPrincipal()).thenReturn(testUserDetails);
        when(jwtUtil.generateToken(any(CustomUserDetails.class))).thenReturn(TestUserFactory.getAccessToken());
        when(userMapper.toLoginResponseDtoFromUserDetails(any(CustomUserDetails.class), any(String.class))).thenReturn(testLoginResponseDto);

        // when
        LoginResponseDto result = authServiceImpl.login(testLoginRequestDto);

        // then
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(testAuthentication).getPrincipal();
        verify(jwtUtil).generateToken(any(CustomUserDetails.class));
        verify(userMapper).toLoginResponseDtoFromUserDetails(any(CustomUserDetails.class), eq(TestUserFactory.getAccessToken()));

        assertThat(result.getUserId()).isEqualTo(testUser.getUserId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(testLoginResponseDto);
    }
}