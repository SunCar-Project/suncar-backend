package com.yangsunkue.suncar.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Querydsl 설정 클래스 입니다.
 *
 * EntityManager : JPA의 핵심 클래스, 엔티티 관리와 DB작업 담당
 * JPAQueryFactory : Querydsl 쿼리를 생성하는 핵심 클래스
 *
 * EntityManager를 주입받아(@PersistenceContext),
 * JPAQueryFactory를 빈으로 등록해두면(@Bean),
 * 다른 클래스들에서 @Autowired로 JPAQueryFactory를 주입받아 QueryDSL을 사용할 수 있다
 * 또는, 생성자 호출 시 자동 주입되도록 할 수도 있다
 */
@Configuration
public class QuerydslConfig {

    @PersistenceContext // JPA의 EntityManager를 주입받기 위한 표준 어노테이션이다.
    private EntityManager entityManager;

    // JPAQueryFactory 빈을 생성하여 스프링 컨테이너에 등록한다.
    // 이 빈은 다른 컴포넌트(빈)에서 QueryDSL을 사용할 때 주입받아 사용 가능하다.
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
