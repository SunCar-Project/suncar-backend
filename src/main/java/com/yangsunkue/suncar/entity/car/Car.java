package com.yangsunkue.suncar.entity.car;

import com.yangsunkue.suncar.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 차량 상세정보를 담는 엔티티입니다.
 */
@Entity
@Table(name = "car")
@SQLDelete(sql = "UPDATE car SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

    @Column(name = "car_name", length = 100, nullable = false)
    private String carName;

    @Column(name = "car_number", length = 20, nullable = false)
    private String carNumber;

    /** 배기량 */
    @Column(name = "displacement", length = 20, nullable = false)
    private String displacement;

    /** 연료 */
    @Column(name = "fuel_type", length = 20, nullable = false)
    private String fuelType;

    /** 연식 */
    @Column(name = "year", nullable = false)
    private Integer year;

    /** 출고 월 */
    @Column(name = "month")
    private Integer month;

    /** 차체형상 */
    @Column(name = "body_shape", length = 20, nullable = false)
    private String bodyShape;

    /** 용도 및 차종 */
    @Column(name = "model_type", length = 20, nullable = false)
    private String modelType;

    /** 최초 보험 가입일자 */
    @Column(name = "first_insurance_date", nullable = false)
    private LocalDate firstInsuranceDate;

    /** 차대번호 */
    @Column(name = "identification_number", length = 50, unique = true, nullable = false)
    private String identificationNumber;

    /** 최소거래가 */
    @Column(name = "min_price")
    private BigDecimal minPrice;

    /** 최대거래가 */
    @Column(name = "max_price")
    private BigDecimal maxPrice;
}






















