package com.yangsunkue.suncar.dto.car;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarAccidentRepairDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long accidentId;
    private String accidType;
    private String totalAmount;
    private BigDecimal partsCost;
    private BigDecimal laborCost;
    private BigDecimal paintingCost;
    private BigDecimal insuranceAmount;
}
