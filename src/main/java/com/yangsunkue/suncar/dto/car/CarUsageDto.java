package com.yangsunkue.suncar.dto.car;

import com.fasterxml.jackson.annotation.JsonInclude;
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
