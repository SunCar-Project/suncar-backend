package com.yangsunkue.suncar.dto.car;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class CarAccidentDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long carId;
    private LocalDate accidentDate;
    private String accidentType;
    private String processingType;
}
