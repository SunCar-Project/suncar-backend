package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.math.BigDecimal;

/**
 * 차량 사고이력 상세정보를 담는 엔티티입니다.
 */
@Entity
@Table(name = "car_accident_repair")
@SQLDelete(sql = "UPDATE car_accident_repair SET is_deleted = true WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarAccidentRepair extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_id", nullable = false)
    private CarAccident carAccident;

    /** 사고구분 (전손, 도난 ...) */
    @Column(name = "accid_type", length = 20)
    private String accidType;

    /**
     * 총 수리비용
     * "미확정" 케이스 때문에 String 타입, 추후 계산 시 BigDecimal 타입으로 변경 필요
     */
    @Column(name = "total_amount", length = 20)
    private String totalAmount;

    /** 부품 비용 */
    @Column(name = "parts_cost")
    private BigDecimal partsCost;

    /** 공임 비용 */
    @Column(name = "labor_cost")
    private BigDecimal laborCost;

    /** 도장 비용 */
    @Column(name = "painting_cost")
    private BigDecimal paintingCost;

    /** 보험금 */
    @Column(name = "insurance_amount")
    private BigDecimal insuranceAmount;
}
