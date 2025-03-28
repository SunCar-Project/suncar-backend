package com.yangsunkue.suncar.dto.auth;

import com.yangsunkue.suncar.entity.user.User;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class LoginResponseDto {

    private String userId;
    private String username;
    private String accessToken;

    public static LoginResponseDto toDto(User user, String accessToken) {
        return new LoginResponseDto(
                user.getUserId(),
                user.getUsername(),
                accessToken
        );
    }
}
