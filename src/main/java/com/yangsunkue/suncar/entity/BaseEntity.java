package com.yangsunkue.suncar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 모든 엔티티가 상속받아야 하는 Base Entity 입니다.
 *
 * 생성일, 마지막 수정일, 삭제여부(SoftDelete) 컬럼을 가지고 있습니다.
 */
@EntityListeners(AuditingEntityListener.class) // 엔티티 생성/수정 시간을 관리하는 리스너
@MappedSuperclass // 이 클래스를 상속받은 엔티티들에게, DB 매핑정보를 제공하는 상위 클래스임을 나타낸다.
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class BaseEntity {

    @CreatedDate // 엔티티가 처음 저장될 때 현재 시간 자동 저장
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티가 수정될 때마다 현재 시간 자동 저장
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    /**
     * Soft delete : 삭제를 위한 메서드
     */
    public void softDelete() {
        this.isDeleted = true;
    }

    /**
     * Soft delete : 복구를 위한 메서드
     */
    public void restore() {
        this.isDeleted = false;
    }
}
