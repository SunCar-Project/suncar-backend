package com.yangsunkue.suncar.util.builder;


import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.util.factory.TestUserFactory;
import jakarta.persistence.EntityManager;

/**
 * 테스트 코드에서 사용할 User 관련 엔티티 영속화를 도와주는 빌더 클래스 입니다.
 */
public class TestUserBuilder extends TestBaseBuilder {

    public TestUserBuilder(EntityManager em) {
        super(em);
    }

    public User createAndPersistAndFlushUser() {
        User user = TestUserFactory.createUnSavedUser();
        persistAndFlush(user);
        return user;
    }
}
