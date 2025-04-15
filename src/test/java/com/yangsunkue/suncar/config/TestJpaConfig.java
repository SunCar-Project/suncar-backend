package com.yangsunkue.suncar.config;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 테스트 환경 전용 JPA 설정 클래스 입니다.
 */
@TestConfiguration
@EnableJpaAuditing
public class TestJpaConfig {

    /** 쿼리 통계 활성화 */
    @Bean
    public JpaProperties jpaProperties() {
        JpaProperties properties = new JpaProperties();
        properties.getProperties().put("hibernate.generate_statistics", "true");
        return properties;
    }
}
