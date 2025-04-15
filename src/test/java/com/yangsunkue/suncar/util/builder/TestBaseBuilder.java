package com.yangsunkue.suncar.util.builder;

import jakarta.persistence.EntityManager;

public abstract class TestBaseBuilder {

    protected final EntityManager em;

    protected TestBaseBuilder(EntityManager em) {
        this.em = em;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * 인자로 받은 엔티티를 영속성 컨텍스트에 등록한 후, DB와 동기화합니다.
     * persist()와 flush()를 동시에 처리합니다.
     */
    protected <T> T persistAndFlush(T entity) {
        em.persist(entity);
        em.flush();
        return entity;
    }
}
