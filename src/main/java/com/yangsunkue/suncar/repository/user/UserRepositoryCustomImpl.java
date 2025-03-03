package com.yangsunkue.suncar.repository.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yangsunkue.suncar.entity.user.User;
import com.yangsunkue.suncar.repository.support.Querydsl4RepositorySupport;
import org.springframework.stereotype.Repository;

import static com.yangsunkue.suncar.entity.user.QUser.user;

/**
 * User 커스텀 리포지토리 구현체입니다.
 */
@Repository
public class UserRepositoryCustomImpl extends Querydsl4RepositorySupport implements UserRepositoryCustom {

    /**
     * QuerydslConfig에서 JPAQueryFactory를 스프링 컨테이너에 빈으로써 등록해 두었다.
     * 스프링은 컨테이너에서 객체를 찾아, 생성자의 인자로 자동으로 전달한다.
     *
     * 이 클래스는 @Repository 어노테이션이 달려 있기 때문에 자동으로 빈으로 등록되고 인식되는 것이다.
     */



    /**
     * 실제 리파지터리(CommentRepository)에서 Impl을 상속받고 사용할 때,
     * 이 생성자가 자동으로 호출되며 JPAQueryFactory에 대한 의존성 주입이 일어난다.
     */
    public UserRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(queryFactory, User.class);
    }

    /**
     * 해당 userId를 가진 레코드가 있는지 찾습니다.
     */
    @Override
    public boolean existsByUserId(String userId) {
         return getQueryFactory()
                 .selectOne()
                 .from(user)
                 .where(user.userId.eq(userId))
                 .fetchFirst() != null;
    }

    /**
     * 해당 email을 가진 레코드가 있는지 찾습니다.
     */
    @Override
    public boolean existsByEmail(String email) {
        return getQueryFactory()
                .selectOne()
                .from(user)
                .where(user.email.eq(email))
                .fetchFirst() != null;
    }

}
