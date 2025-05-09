package com.yangsunkue.suncar.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * 테스트 환경 전용 QueryDsl 설정 클래스 입니다.
 */
@TestConfiguration
public class TestQuerydslConfig {

    /** 영속성 컨텍스트를 EntityManager로 관리 */
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
