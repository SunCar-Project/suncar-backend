package com.yangsunkue.suncar.dto.car;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;


/**
 * 기존 사고이력 Dto에 수리정보를 포함하기 위한 Dto 입니다.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Getter
@Setter
public class CarAccidentWithRepairsDto extends CarAccidentDto {

    private List<CarAccidentRepairDto> repairs;
}
