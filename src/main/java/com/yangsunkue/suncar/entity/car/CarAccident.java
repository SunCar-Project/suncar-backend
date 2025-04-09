package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;

/**
 * 차량 사고이력을 담는 엔티티입니다.
 */
@Entity
@Table(name = "car_accident")
@SQLDelete(sql = "UPDATE car_accident SET is_deleted = true WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarAccident extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    /** 사고일자 */
    @Column(name = "accident_date")
    private LocalDate accidentDate;

    /** 누구 피해인지 (내차/상대차 피해) */
    @Column(name = "accident_type", length = 20)
    private String accidentType;

    /** 누구 보험으로 처리했는지 (나/상대 보험처리) */
    @Column(name = "processing_type", length = 20)
    private String processingType;
}
