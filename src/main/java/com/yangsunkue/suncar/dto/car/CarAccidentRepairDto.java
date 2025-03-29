package com.yangsunkue.suncar.dto.car;

import com.yangsunkue.suncar.entity.car.CarAccident;
import com.yangsunkue.suncar.entity.car.CarAccidentRepair;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class CarAccidentRepairDto {

    private Long accidentId;

    private String accidType;

    private String totalAmount;

    private BigDecimal partsCost;

    private BigDecimal laborCost;

    private BigDecimal paintingCost;

    private BigDecimal insuranceAmount;

    public static CarAccidentRepair toEntity(CarAccidentRepairDto dto) {
        return CarAccidentRepair.builder()
                .carAccident(CarAccident.builder().id(dto.getAccidentId()).build())
                .accidType(dto.getAccidType())
                .totalAmount(dto.getTotalAmount())
                .partsCost(dto.getPartsCost())
                .laborCost(dto.getLaborCost())
                .paintingCost(dto.getPaintingCost())
                .insuranceAmount(dto.getInsuranceAmount())
                .build();
    }
}
