package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

/**
 * 차량 사용이력을 담는 엔티티입니다.
 */
@Entity
@Table(name = "car_usage")
@SQLDelete(sql = "UPDATE car_usage SET is_deleted = true WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarUsage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    /**
     * 모든 사용이력은 있음/없음 으로 구분됩니다.
     */

    /** 렌트 */
    @Column(name = "rental_history", length = 5, nullable = false)
    private String rentalHistory;

    /** 일반영업 */
    @Column(name = "business_history", length = 5, nullable = false)
    private String businessHistory;

    /** 관용용도 */
    @Column(name = "government_history", length = 5, nullable = false)
    private String governmentHistory;
}
