package com.yangsunkue.suncar.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 인증 필터 클래스 입니다.
 * 모든 요청에 대해 JWT 토큰을 확인하고 유효한 경우 인증 정보를 설정합니다.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 요청에서 토큰 추출
        String jwt = resolveToken(request);
        String requestURI = request.getRequestURI();

        // 토큰이 유효한 경우
        if (StringUtils.hasText(jwt) && jwtUtil.isTokenValid(jwt)) {

            // 유저 정보로 인증 정보 설정
            Authentication authentication = jwtUtil.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("인증 정보 저장 완료: {}, URI: {}", authentication.getName(), requestURI);
        }
        else {
            log.debug("유효한 JWT 토큰이 없습니다. URI: {}", requestURI);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Authorization 헤더에서 Bearer 토큰을 추출합니다.
     */
    private String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }

}
