package com.yangsunkue.suncar.entity.user;

import com.yangsunkue.suncar.dto.user.request.UserProfileUpdateRequestDto;
import com.yangsunkue.suncar.entity.BaseEntity;
import com.yangsunkue.suncar.common.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

/**
 * 회원 정보를 담는 엔티티입니다.
 */
@Entity
@Table(name = "users") // 테이블명은 DB에서 복수형이 관례, 자바는 단수형이 관례
@SQLDelete(sql = "UPDATE users SET is_deleted = true WHERE id = ?") // JPA delete 메서드를 softdelete로 동작하게 함
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Builder(toBuilder = true)
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

    public void patch(UserProfileUpdateRequestDto dto) {
        if (dto.getUserName() != null && !dto.getUserName().isEmpty()) {
            this.username = dto.getUserName();
        }
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().isEmpty()) {
            this.phoneNumber = dto.getPhoneNumber();
        }
    }
}