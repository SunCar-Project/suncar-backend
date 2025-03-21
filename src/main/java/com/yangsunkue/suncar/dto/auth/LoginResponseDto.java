package com.yangsunkue.suncar.dto.auth;

import com.yangsunkue.suncar.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
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
