package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.CarMileage;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class CarMileageDto {

    private Long carId;

    private Integer distance;

    private String provider;

    private LocalDate recordDate;

    public static CarMileage toEntity(CarMileageDto dto) {
        return CarMileage.builder()
                .car(Car.builder().id(dto.getCarId()).build())
                .distance(dto.getDistance())
                .provider(dto.getProvider())
                .recordDate(dto.getRecordDate())
                .build();
    }
}