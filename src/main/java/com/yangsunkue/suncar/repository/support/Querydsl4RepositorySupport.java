package com.yangsunkue.suncar.repository.support;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

/**
 * 모든 QueryDSL 리포지터리 공통 기능을 제공하는 베이스 클래스 입니다.
 *
 * domainClass : 어떤 엔티티를 다루는지 타입 정보를 받는다
 *
 * @Repository
 * - 스프링의 컴포넌트 스캔 대상이 된다(자동으로 빈으로 등록)
 * - 데이터 액세스 계층의 빈임을 명시
 * - 트랜잭션 처리가 필요한 빈임을 명시
 * - JPA 예외를 스프링의 DataAccessException으로 변환
 */
@Repository
public abstract class Querydsl4RepositorySupport {

    private final JPAQueryFactory queryFactory;

    public Querydsl4RepositorySupport(JPAQueryFactory queryFactory, Class<?> domainClass) {
        this.queryFactory = queryFactory;
    }

    protected JPAQueryFactory getQueryFactory() {
        return queryFactory;
    }
}
