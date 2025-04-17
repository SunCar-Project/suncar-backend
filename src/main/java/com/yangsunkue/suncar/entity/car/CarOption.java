package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.common.enums.OptionInstallStatus;
import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

/**
 * 차량 안전창치 및 옵션을 담는 엔티티입니다.
 */
@Entity
@Table(name = "car_option")
@SQLDelete(sql = "UPDATE car_option SET is_deleted = true WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(toBuilder = true)
@Getter
public class CarOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    /** 안전장치/옵션명 */
    @Column(name = "option_name", length = 20, nullable = false)
    private String optionName;

    /** 설치 여부 */
    @Column(name = "install_status")
    @Enumerated(EnumType.STRING)
    private OptionInstallStatus installStatus;
}
