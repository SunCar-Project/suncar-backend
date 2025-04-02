package com.yangsunkue.suncar.mapper;

import com.yangsunkue.suncar.dto.auth.request.SignUpRequestDto;
import com.yangsunkue.suncar.dto.auth.response.LoginResponseDto;
import com.yangsunkue.suncar.dto.auth.response.SignUpResponseDto;
import com.yangsunkue.suncar.dto.user.response.UserProfileResponseDto;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.security.CustomUserDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * User 엔티티 관련 매퍼 인터페이스 입니다.
 */
@Mapper(config = BaseMapperConfig.class)
public interface UserMapper {

    /** to Entity */
    @Mapping(source = "passwordHash", target = "passwordHash")
    User fromSignUpRequestDto(SignUpRequestDto dto, String passwordHash);


    /** to Dto */
    @Mapping(source = "accessToken", target = "accessToken")
    LoginResponseDto toLoginResponseDtoFromUserDetails(CustomUserDetails userDetails, String accessToken);
    SignUpResponseDto toSignUpResponseDto(User user);
    @Mapping(source = "username", target = "userName")
    UserProfileResponseDto toUserProfileResponseDto(User user);
    @Mapping(source = "username", target = "userName")
    UserProfileResponseDto toUserProfileResponseDtoFromUserDetails(CustomUserDetails userDetails);
}