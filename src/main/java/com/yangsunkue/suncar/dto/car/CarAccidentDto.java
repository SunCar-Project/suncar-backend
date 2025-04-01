package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.CarAccident;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarAccidentDto {

    private Long carId;
    private LocalDate accidentDate;
    private String accidentType;
    private String processingType;
}
