package com.yangsunkue.suncar.dto.car.response;

import com.yangsunkue.suncar.dto.car.CarListingDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Setter
public class UpdateCarListingResponseDto extends CarListingDto {

    private Long id;
}
