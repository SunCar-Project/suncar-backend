package com.yangsunkue.suncar.service.auth;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.auth.request.LoginRequestDto;
import com.yangsunkue.suncar.dto.auth.request.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.response.LoginResponseDto;
import com.yangsunkue.suncar.dto.auth.response.SignUpResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.DuplicateResourceException;
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
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
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

        testAuthentication = mock(Authentication.class);
        testUserDetails = mock(CustomUserDetails.class);
        testUser = TestUserFactory.createUser();

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
    void createUser() {

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
        assertThat(result.getId()).isEqualTo(testUser.getId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(testSignUpResponseDto);

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
    }

    @Test
    @DisplayName("일반 회원가입 시 사용자 ID가 중복될 경우 DuplicateResourceException 반환하는지 테스트")
    void shouldThrowDuplicateResourceWhenUserIdIsDuplicate() {

        // given
        String duplicateUserId = "abc123";
        testSignUpRequestDto.setUserId(duplicateUserId);
        when(userRepository.existsByUserId(duplicateUserId)).thenReturn(true);

        // when
        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class,
                () -> authServiceImpl.createUser(testSignUpRequestDto));

        // then
        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.DUPLICATE_USER_ID);
        verify(userRepository).existsByUserId(duplicateUserId);
        verify(userRepository, never()).existsByEmail(any(String.class));
    }

    @Test
    @DisplayName("일반 회원가입 시 사용자 이메일이 중복될 경우 DuplicateResourceException 반환하는지 테스트")
    void shouldThrowDuplicateResourceWhenEmailIsDuplicate() {

        // given
        String duplicateEmail = "abc123@gmail.com";
        testSignUpRequestDto.setEmail(duplicateEmail);

        when(userRepository.existsByUserId(testSignUpRequestDto.getUserId())).thenReturn(false);
        when(userRepository.existsByEmail(duplicateEmail)).thenReturn(true);

        // when
        DuplicateResourceException exception = assertThrows(DuplicateResourceException.class,
                () -> authServiceImpl.createUser(testSignUpRequestDto));

        // then
        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.DUPLICATE_EMAIL);

        verify(userRepository).existsByUserId(testSignUpRequestDto.getUserId());
        verify(userRepository).existsByEmail(duplicateEmail);
        verify(passwordEncoder, never()).encode(any(String.class));
    }

    @Test
    @DisplayName("일반 로그인 테스트")
    void login() {

        // given
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(testAuthentication);
        when(testAuthentication.getPrincipal()).thenReturn(testUserDetails);
        when(jwtUtil.generateToken(any(CustomUserDetails.class))).thenReturn(TestUserFactory.getAccessToken());
        when(userMapper.toLoginResponseDtoFromUserDetails(any(CustomUserDetails.class), any(String.class))).thenReturn(testLoginResponseDto);

        // when
        LoginResponseDto result = authServiceImpl.login(testLoginRequestDto);

        // then
        assertThat(result.getUserId()).isEqualTo(testUser.getUserId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(testLoginResponseDto);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(testAuthentication).getPrincipal();
        verify(jwtUtil).generateToken(any(CustomUserDetails.class));
        verify(userMapper).toLoginResponseDtoFromUserDetails(any(CustomUserDetails.class), eq(TestUserFactory.getAccessToken()));
    }

    @Test
    @DisplayName("로그인 실패 시 BadCredentialsException 반환하는지 테스트")
    void shouldThrowBadCredentialsWhenLoginFails() {

        // given
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // when
        assertThrows(BadCredentialsException.class,
                () -> authServiceImpl.login(testLoginRequestDto));

        // then
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(testAuthentication, never()).getPrincipal();
    }
}