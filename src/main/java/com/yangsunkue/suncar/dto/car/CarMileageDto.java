package com.yangsunkue.suncar.dto.car;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class CarMileageDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long carId;
    private Integer distance;
    private String provider;
    private LocalDate recordDate;
}