package com.yangsunkue.suncar.security;

import com.yangsunkue.suncar.common.enums.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * JWT에 사용될 UserDetails를 커스텀한 클래스 입니다.
 */
@Getter
@Builder
@ToString
public class CustomUserDetails implements UserDetails {

    private final String userId; // 로그인 아이디
    private final String username; // 사용자 이름
    private final String password;
    private final String email;
    private final String phoneNumber;
    private final UserRole role;

    @Builder.Default
    private final boolean enabled = true;
    @Builder.Default
    private final boolean accountNonExpired = true;
    @Builder.Default
    private final boolean accountNonLocked = true;
    @Builder.Default
    private final boolean credentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // enum의 name()을 사용하여 문자열로 변환 후 권한 생성
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }
}