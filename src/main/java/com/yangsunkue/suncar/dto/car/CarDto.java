package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.Model;
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

    public static Car toEntity(CarDto dto) {
        return Car.builder()
                .model(Model.builder().id(dto.getModelId()).build())
                .carName(dto.getCarName())
                .carNumber(dto.getCarNumber())
                .displacement(dto.getDisplacement())
                .fuelType(dto.getFuelType())
                .year(dto.getYear())
                .month(dto.getMonth())
                .bodyShape(dto.getBodyShape())
                .modelType(dto.getModelType())
                .firstInsuranceDate(dto.getFirstInsuranceDate())
                .identificationNumber(dto.getIdentificationNumber())
                .minPrice(dto.getMinPrice())
                .maxPrice(dto.getMaxPrice())
                .build();
    }
}
