package com.yangsunkue.suncar.service.user;

import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.dto.user.request.UserProfileUpdateRequestDto;
import com.yangsunkue.suncar.dto.user.response.UserProfileResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.mapper.UserMapper;
import com.yangsunkue.suncar.repository.user.UserRepository;
import com.yangsunkue.suncar.security.CustomUserDetails;
import com.yangsunkue.suncar.util.RandomUtils;
import com.yangsunkue.suncar.util.factory.TestUserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    /** 의존성 */
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserMapper userMapper;

    /** 테스트 데이터 */
    private User testUser;
    private CustomUserDetails testUserDetails;
    private UserProfileUpdateRequestDto testUserProfileUpdateRequestDto;
    private UserProfileResponseDto testUserProfileResponseDto;

    /** 테스트 데이터 초기화 */
    @BeforeEach
    void setup() {

        testUser = TestUserFactory.createUser();
        testUserDetails = mock(CustomUserDetails.class);

        testUserProfileUpdateRequestDto = UserProfileUpdateRequestDto.builder()
                .userName(testUser.getUsername())
                .phoneNumber(testUser.getPhoneNumber())
                .build();

        testUserProfileResponseDto = UserProfileResponseDto.builder()
                .userId(testUser.getUserId())
                .email(testUser.getEmail())
                .userName(testUser.getUsername())
                .phoneNumber(testUser.getPhoneNumber())
                .role(testUser.getRole())
                .build();
    }

    @Test
    @DisplayName("현재 사용자 정보 수정 테스트")
    void updateCurrentUserProfile() {

        // given
        when(testUserDetails.getUserId()).thenReturn(testUser.getUserId());
        when(userRepository.findByUserId(testUserDetails.getUserId())).thenReturn(Optional.of(testUser));
        when(userMapper.toUserProfileResponseDto(testUser)).thenReturn(testUserProfileResponseDto);

        // when
        UserProfileResponseDto result = userServiceImpl.updateCurrentUserProfile(testUserDetails, testUserProfileUpdateRequestDto);

        // then
        assertThat(result.getUserId()).isEqualTo(testUser.getUserId());
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(testUserProfileResponseDto);

        verify(userRepository).findByUserId(testUserDetails.getUserId());
        verify(userMapper).toUserProfileResponseDto(testUser);
    }

    @Test
    @DisplayName("사용자 정보 수정 시 존재하지 않는 유저일 경우 Not Found 반환하는지 테스트")
    void shouldThrowNotFoundWhenUserDoesNotExist() {

        // given
        String nonExistentUserId = RandomUtils.createUuid(32);
        when(testUserDetails.getUserId()).thenReturn(nonExistentUserId);
        when(userRepository.findByUserId(testUserDetails.getUserId())).thenReturn(Optional.empty());

        // when
        NotFoundException exception = assertThrows(NotFoundException.class,
                () -> userServiceImpl.updateCurrentUserProfile(
                        testUserDetails,
                        testUserProfileUpdateRequestDto
                ));

        // then
        assertThat(exception.getMessage()).isEqualTo(ErrorMessages.USER_NOT_FOUND);
        verify(userRepository).findByUserId(nonExistentUserId);
        verify(userMapper, never()).toUserProfileResponseDto(any(User.class));
    }
}