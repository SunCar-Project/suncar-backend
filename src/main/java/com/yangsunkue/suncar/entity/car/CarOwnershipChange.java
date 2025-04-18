package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDate;

/**
 * 차량 번호/소유자 변경이력을 담는 엔티티 입니다.
 */
@Entity
@Table(name = "car_ownership_change")
@SQLDelete(sql = "UPDATE car_ownership_change SET is_deleted = true WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Getter
public class CarOwnershipChange extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "change_date", nullable = false)
    private LocalDate changeDate;

    @Column(name = "change_type", length = 20)
    private String changeType;

    @Column(name = "car_number", length = 20)
    private String carNumber;

    /** 차량용도 (자가용 승용 ..) */
    @Column(name = "usage_purpose", length = 20)
    private String usagePurpose;
}
