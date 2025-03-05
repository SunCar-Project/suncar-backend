package com.yangsunkue.suncar.entity.user;

import com.yangsunkue.suncar.entity.BaseEntity;
import com.yangsunkue.suncar.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

/**
 * 회원 정보를 담는 엔티티입니다.
 */
@Entity
@Table(name = "users") // 테이블명은 DB에서 복수형이 관례, 자바는 단수형이 관례
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id = ?") // delete 메서드를 softdelete로 동작하게 함
@SQLRestriction("is_deleted = false") // 조회 시 삭제되지 않은 컬럼 조건 자동 추가
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    private String userId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password_hash", length = 2000, nullable = false)
    private String passwordHash;

    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    /**
     * 계정 권한 - 회원, 딜러, 관리자
     */
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
