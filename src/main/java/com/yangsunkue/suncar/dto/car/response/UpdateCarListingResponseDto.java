package com.yangsunkue.suncar.dto.car.response;

import com.yangsunkue.suncar.dto.car.CarListingDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@Setter
@Getter
public class UpdateCarListingResponseDto extends CarListingDto {

    private Long listingId;
}
