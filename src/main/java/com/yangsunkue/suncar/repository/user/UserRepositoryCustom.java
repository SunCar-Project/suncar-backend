package com.yangsunkue.suncar.repository.user;

/**
 * User 커스텀 리포지토리 구현을 위한 인터페이스 입니다.
 * Querydsl4RepositorySupport과 함께 상속받아 Querydsl을 사용할 수 있습니다.
 */
public interface UserRepositoryCustom {

    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
}
