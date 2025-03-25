package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.common.enums.BrandName;
import com.yangsunkue.suncar.common.enums.ModelName;
import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

/**
 * 차량 브랜드, 모델명을 담는 엔티티입니다.
 */
@Entity
@Table(name = "model")
@SQLDelete(sql = "UPDATE model SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@AllArgsConstructor
@NoArgsConstructor
public class Model extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private BrandName brandName;

    @Column(name = "model_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private ModelName modelName;

    // 외제차 여부
    @Column(name = "is_foreign", nullable = false)
    private Boolean isForeign;
}