package com.yangsunkue.suncar.dto.auth;

import com.yangsunkue.suncar.entity.user.User;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class LoginResponseDto {

    private String userId;
    private String username;
    private String accessToken;

    public static LoginResponseDto fromUserAndToken(User user, String accessToken) {
        return LoginResponseDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .accessToken(accessToken)
                .build();
    }
}
