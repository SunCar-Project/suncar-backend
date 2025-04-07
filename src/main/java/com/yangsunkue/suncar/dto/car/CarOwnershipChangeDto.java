package com.yangsunkue.suncar.dto.car;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.CarOwnershipChange;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarOwnershipChangeDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long carId;
    private LocalDate changeDate;
    private String changeType;
    private String carNumber;
    private String usagePurpose;
}
