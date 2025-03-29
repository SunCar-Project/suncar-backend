package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.CarUsage;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarUsageDto {

    private Long carId;

    private String rentalHistory;

    private String businessHistory;

    private String governmentHistory;

    public static CarUsage toEntity(CarUsageDto dto) {
        return CarUsage.builder()
                .car(Car.builder().id(dto.getCarId()).build())
                .rentalHistory(dto.getRentalHistory())
                .businessHistory(dto.getBusinessHistory())
                .governmentHistory(dto.getGovernmentHistory())
                .build();
    }
}
