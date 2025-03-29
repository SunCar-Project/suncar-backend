package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.CarOwnershipChange;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarOwnershipChangeDto {

    private Long carId;

    private LocalDate changeDate;

    private String changeType;

    private String carNumber;

    private String usagePurpose;

    public static CarOwnershipChange toEntity(CarOwnershipChangeDto dto) {
        return CarOwnershipChange.builder()
                .car(Car.builder().id(dto.getCarId()).build())
                .changeDate(dto.getChangeDate())
                .changeType(dto.getChangeType())
                .carNumber(dto.getCarNumber())
                .usagePurpose(dto.getUsagePurpose())
                .build();
    }
}
