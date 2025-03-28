package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.Model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarDto {

    @NotNull
    private Long modelId;

    @NotBlank
    @Size(max = 100)
    private String carName;

    @NotBlank
    @Size(max = 20)
    private String carNumber;

    @NotBlank
    @Size(max = 20)
    private String displacement;

    @NotBlank
    @Size(max = 20)
    private String fuelType;

    @NotNull
    private Integer year;

    private Integer month;

    @NotBlank
    @Size(max = 20)
    private String bodyShape;

    @NotBlank
    @Size(max = 20)
    private String modelType;

    @NotNull
    private LocalDate firstInsuranceDate;

    @NotBlank
    @Size(max = 50)
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
