package com.yangsunkue.suncar.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger API 명세서 사용을 위한 OpenApi 설정 클래스 입니다.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()

                // 기본 정보
                .info(new Info()
                        .title("Suncar API")
                        .version("1.0")
                        .description("Suncar API 명세서")
                        .contact(new Contact()
                                .name("양선규")
                                .email("ysk9526@gmail.com")))

                // JWT 인증 설정 추가
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("JWT 토큰을 헤더에 입력하세요.")));
    }
}