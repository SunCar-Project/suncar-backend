package com.yangsunkue.suncar.dto.car;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yangsunkue.suncar.entity.car.Car;
import com.yangsunkue.suncar.entity.car.CarUsage;
import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarUsageDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long carId;
    private String rentalHistory;
    private String businessHistory;
    private String governmentHistory;
}
