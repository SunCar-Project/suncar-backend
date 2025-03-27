package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;

/**
 * 차량 주행거리를 담는 엔티티 입니다.
 */
@Entity
@Table(name = "car_mileage")
@SQLDelete(sql = "UPDATE car_mileage SET is_deleted = true WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class CarMileage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    /** 주행거리 */
    @Column(name = "distance", nullable = false)
    private Integer distance;

    /** 제공처 */
    @Column(name = "provider", length = 50, nullable = false)
    private String provider;

    /** 기록일자 */
    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;
}