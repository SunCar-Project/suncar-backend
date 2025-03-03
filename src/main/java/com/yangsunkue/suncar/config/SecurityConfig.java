package com.yangsunkue.suncar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 설정 클래스입니다.
 *
 * 초기 개발 편의를 위해서,
 * 모든 요청에 대한 인증 및 csrf 공격 보호를 비활성화 했습니다.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/**").permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}