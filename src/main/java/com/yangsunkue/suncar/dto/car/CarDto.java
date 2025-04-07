package com.yangsunkue.suncar.dto.car;

import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarDto {

    private Long modelId;
    private String carName;
    private String carNumber;
    private String displacement;
    private String fuelType;
    private Integer year;
    private Integer month;
    private String bodyShape;
    private String modelType;
    private LocalDate firstInsuranceDate;
    private String identificationNumber;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
