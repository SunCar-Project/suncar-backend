package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.common.enums.CarListingStatus;
import com.yangsunkue.suncar.entity.BaseEntity;
import com.yangsunkue.suncar.entity.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.math.BigDecimal;

/**
 * 차량 판매 등록 정보를 담는 엔티티입니다.
 */
@Entity
@Table(name = "car_listing")
@SQLDelete(sql = "UPDATE car_listing SET is_deleted = true WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarListing extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User user;

    /** 판매가격 */
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    /** 판매 설명글 */
    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    /** 판매상태 */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CarListingStatus status;
}
