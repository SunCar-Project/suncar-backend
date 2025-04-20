package com.yangsunkue.suncar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {

    /**
     * findById 메서드에 where 조건을 추가합니다.
     * soft delete 되지 않은 데이터만 조회합니다.
     * where is_delete = false
     */
    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.isDeleted = false")
    Optional<T> findById(@Param("id") ID id);
}
