package com.yangsunkue.suncar.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT 생성, 검증, 파싱을 위한 클래스 입니다.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {

    private final CustomUserDetailsService customUserDetailsService;

    @Value("${jwt.secret}") // 시크릿 키 가져오기
    private String secret;

    @Value("${jwt.expiration}") // 토큰 만료 시간 가져오기
    private Long expiration;

    /**
     * 서명 키를 생성합니다.
     *
     * 사용자 정의 시크릿 키를 해시에 사용할 수 있도록 바이트화해서 리턴 -> 서명 키
     */
    private Key getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 액세스 토큰을 받아옵니다.
     */
    public String generateToken(CustomUserDetails userDetails) {

        /**
         * 사용자 정의 클레임
         * 필요 시 토큰에 데이터를 추가할 수 있다.
         * ex) claims.put("email", userDetails.getEmail());
         */
        Map<String, Object> claims = new HashMap<>();

        // userId를 고유 식별자 Subject로 사용
        return createToken(claims, userDetails.getUserId());
    }

    /**
     * 실제로 액세스 토큰을 생성하는 메서드 입니다.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims) // 추가 정보들 ( 사용자 정의 클레임 )
                .setSubject(subject) // 토큰의 주체 ( userId)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 발행 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // 만료 시간
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 서명
                .compact(); // 최종 JWT 문자열 생성
    }

    /**
     * 액세스 토큰의 유효성을 검증합니다.
     */
    public Boolean isTokenValid(String token) {

        try {
            extractAllClaims(token); // 토큰 파싱 -> 여기서 예외 발생가능
            return !isTokenExpired(token); // 토큰 만료여부 리턴

        } catch (Exception e) {
            log.error("JWT 토큰 검증 실패: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 액세스 토큰에서 사용자 아이디(Subject)를 추출합니다.
     */
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 액세스 토큰에서 만료 날짜(Expiration)를 추출합니다.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 액세스 토큰에서 특정 클레임을 추출하는 메서드 입니다.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 액세스 토큰을 파싱합니다.
     * 서명 검증에 실패할 경우 예외가 발생합니다.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 토큰이 만료되었는지 확인합니다.
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * 토큰을 받아 Authentication 객체를 생성합니다.
     */
    public Authentication getAuthentication(String token) {

        String userId = extractUserId(token);
        CustomUserDetails userDetails = customUserDetailsService.loadUserByUsername(userId);

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
    }
}

