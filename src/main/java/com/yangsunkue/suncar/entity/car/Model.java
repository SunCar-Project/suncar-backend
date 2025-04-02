package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;

/**
 * 차량 브랜드, 모델명을 담는 엔티티입니다.
 */
@Entity
@Table(name = "model",
    uniqueConstraints = @UniqueConstraint(columnNames = {"brand_name", "model_name"}))
@SQLDelete(sql = "UPDATE model SET is_deleted = true WHERE id = ?")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class Model extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "model_name", nullable = false)
    private String modelName;

    /** 외제차 여부 */
    @Column(name = "is_foreign", nullable = false)
    private Boolean isForeign;
}