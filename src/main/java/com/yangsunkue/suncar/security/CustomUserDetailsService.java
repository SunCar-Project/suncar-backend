package com.yangsunkue.suncar.security;


import com.yangsunkue.suncar.common.constant.ErrorMessages;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.exception.NotFoundException;
import com.yangsunkue.suncar.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * JWT에 사용될 UserDetailService를 커스텀한 클래스 입니다.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * UserId를 받아 CustomUserDetails 객체를 반환합니다.
     */
    @Override
    @Transactional(readOnly = true)
    public CustomUserDetails loadUserByUsername(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(ErrorMessages.USER_NOT_FOUND));

        return CustomUserDetails.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .password(user.getPasswordHash())
                .role(user.getRole())
                .enabled(!user.getIsDeleted())
                .build();
    }
}