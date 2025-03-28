package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.CarAccident;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarAccidentDto {

    @NotNull
    private Long carId;

    private LocalDate accidentDate;

    @Size(max = 20)
    private String accidentType;

    @Size(max = 20)
    private String processingType;

    public static CarAccident toEntity(CarAccidentDto dto) {
        return CarAccident.builder()
                .car(Car.builder().id(dto.getCarId()).build())
                .accidentDate(dto.getAccidentDate())
                .accidentType(dto.getAccidentType())
                .processingType(dto.getProcessingType())
                .build();
    }
}
