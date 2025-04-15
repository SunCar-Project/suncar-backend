package com.yangsunkue.suncar.util;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * 쿼리 테스트 시 필요한 유틸 메서드를 담는 클래스 입니다.
 */
public class TestQueryUtils {

    /**
     * Hibernate 쿼리 실행 횟수를 반환합니다.
     *
     * @param em 엔티티 매니저
     * @return 실행된 쿼리 횟수
     */
    public static int getQueryCount(EntityManager em) {
        SessionFactory sessionFactory = em.unwrap(Session.class).getSessionFactory();
        return (int) sessionFactory.getStatistics().getQueryExecutionCount();
    }

    /**
     * 엔티티 매니저 캐시를 비우고 Hibernate 쿼리 통계를 초기화합니다.
     *
     * @param em 엔티티 매니저
     */
    public static void clearCacheAndResetStatistics(EntityManager em) {
        em.clear();
        SessionFactory sessionFactory = em.unwrap(Session.class).getSessionFactory();
        sessionFactory.getStatistics().clear();
    }

    /**
     * LocalDateTime을 특정 정밀도까지만 비교하는 Comparator를 반환합니다.
     * 기본값으로 초(SECONDS) 단위까지만 비교합니다.
     *
     * @return LocalDateTime 비교용 Comparator
     */
    public static Comparator<LocalDateTime> getDateTimeComparator() {
        return getDateTimeComparator(ChronoUnit.SECONDS);
    }

    /**
     * LocalDatetime을 지정된 정밀도까지만 비교하는 Comparator를 반환합니다.
     *
     * @param precision 비교할 정밀도 (예: ChronoUnit.SECONDS, ChronoUnit.MILLIS)
     * @return LocalDateTime 비교용 Comparator
     */
    public static Comparator<LocalDateTime> getDateTimeComparator(ChronoUnit precision) {
        return (dt1, dt2) -> {
            if (dt1 == null && dt2 == null) { return 0; }
            if (dt1 == null) { return -1; }
            if (dt2 == null) { return 1; }
            return dt1.truncatedTo(precision).compareTo(dt2.truncatedTo(precision));
        };
    }
}
