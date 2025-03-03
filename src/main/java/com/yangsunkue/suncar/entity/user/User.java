package com.yangsunkue.suncar.entity.user;

import com.yangsunkue.suncar.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

/**
 * 회원 정보를 담는 엔티티입니다.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class User {

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
